package com.antra.videomanager.tool.mapper.support;

import com.antra.videomanager.domain.entity.Company;
import com.antra.videomanager.domain.entity.People;
import com.antra.videomanager.domain.entity.User;
import com.antra.videomanager.tool.mapper.manager.ORMCacheManager;
import com.antra.videomanager.tool.mapper.MapperExecutor;
import com.antra.videomanager.tool.mapper.Mapper;
import org.hibernate.collection.internal.PersistentSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;

@Mapper
public class SimpleMapper implements MapperExecutor {

    /*
        targetClazz => target instance class type
        obj => instance contains field and values
     */
    private final Set<Class<?>> builtInClazzSet = new HashSet<>();
    private final static Set<Class<?>> builtInPOJOSet = new HashSet<>();
    private final Set<Class<?>> builtInCollectionSet = new HashSet<>();

    private ORMCacheManager em;

    public SimpleMapper() {

    }

    @Autowired
    public SimpleMapper(@Qualifier("jpaCacheManager") ORMCacheManager em) {
        this.em = em;
        init();
    }

    private void init() {
        builtInClazzSet.addAll(Arrays.asList(
                Integer.class,
                int.class,
                String.class,
                Long.class,
                long.class,
                Character.class,
                char.class,
                Boolean.class,
                boolean.class,
                Timestamp.class));
        builtInCollectionSet.addAll(Arrays.asList(
                List.class,
                ArrayList.class,
                LinkedList.class,
                Set.class,
                HashSet.class,
                TreeSet.class
        ));
    }

    /*
         1. can't copy parent class or subclass
         XX 2. can't match references map value
               eg User{Company{companyId, companyName}} => User{userinfo, companyId, companyName}
         3. can't handle group by operation
               eg User{Role{1}, Role{2}} => UserDTO{Roles[1, 2]}
         4. can't mapping different name fields
               eg User{role = 1} => UserDTO{roles = [1]} or UserDTO{roles = 1}


         parameters:
                    targetClazz => target object class type
                    obj => original object with key and value pairs
         return:
                target object contains values from obj object
         */
    public Object copyOf(Class<?> targetClazz, Object obj) throws IllegalAccessException, InstantiationException {
        return copyOfHelper(targetClazz, obj, null);
    }

    public Object copyOf(Class<?> targetClazz, Object obj, Map<String, String> fieldMapper) throws IllegalAccessException, InstantiationException {
        return copyOfHelper(targetClazz, obj, fieldMapper);
    }

    private Object copyOfHelper(Class<?> targetClazz, Object obj, Map<String, String> customFieldMapper) throws IllegalAccessException, InstantiationException {
        Object targetObj = initObj(targetClazz);
        Map<String, List<Object>> fieldNameMap = new HashMap<>();
        Map<Field, Object> fieldValueMap = getDeepFieldValueMap(obj, new HashSet<>(), fieldNameMap);
        deepCopyHelper(targetObj, fieldValueMap, fieldNameMap, customFieldMapper);
        return targetObj;
    }

    private void deepCopyHelper(Object obj, Map<Field, Object> fieldValueMap, Map<String, List<Object>> fieldNameMap, Map<String, String> customFieldMapper) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field: fields) {
            String name = field.getName();
            Class<?> type = field.getType();
            field.setAccessible(true);
            Object value = field.get(obj);
            if(customFieldMapper != null && customFieldMapper.containsKey(name)) {
                name = customFieldMapper.get(name);
            }
            List<Object> fieldList = fieldNameMap.get(name);
            if(fieldList != null && fieldList.size() != 0) {
                /*
                need to change later
                1. not verify type
                2. not consider multiple fields in list
                 */
                if(builtInClazzSet.contains(type)) {
                    field.set(obj, fieldList.get(0));
                } else if(builtInCollectionSet.contains(type)) {
                    for(Object val: fieldList) {
                        ((Collection)value).add(val);
                    }
                } else {
                    /*
                    need to change
                    1. List<People> => currently, only mapping first person object in people list
                     */
                    deepCopyHelper(value, (Map<Field, Object>)fieldValueMap.get(fieldList.get(0)), fieldNameMap, customFieldMapper);
                }
            }
        }
    }

    private Object initObj(Class<?> clazz) throws NullPointerException, IllegalAccessException, InstantiationException {
        Object obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields) {
            Class<?> type = field.getType();
            String name = field.getName();
            if(builtInClazzSet.contains(type)) {
                continue;
            }
            Object o = null;
            if(builtInCollectionSet.contains(type)) {
                if(type.equals(List.class)) {
                    o = new ArrayList<>();
                } else if(type.equals(Set.class)) {
                    o = new HashSet<>();
                }
            } else if(builtInClazzSet.contains(type)) {

            } else {
                o = type.newInstance();
                initObj(type);
            }
            field.setAccessible(true);
            field.set(obj, o);
        }
        return obj;
    }

    /*
        Get everything inside of this instance, example:
        peopleSet:
            firstName: p2 first name
            personId: p2 id
            firstName: yang
            lastName: ren
            user:
                password: pwd
                userId: 123-123-42
                username: tom
            email: 66@gaml.com
            personId: personid
        companyName: company Name
        companyId: companyId 123
     */
    private Map<Field, Object> getDeepFieldValueMap(Object obj, Set<Class> visitedNonBuiltInClazz, Map<String, List<Object>> fieldNameMap) throws IllegalAccessException, InstantiationException {
        Class<?> objClazz = obj.getClass();
        if(!builtInPOJOSet.contains(objClazz) && !(Collection.class.isAssignableFrom(objClazz)) && !builtInClazzSet.contains(objClazz)) {
            //if class not belongs to {pojo} , {int, long, Integer..} , {Collection, set , ..} then return null;
            return null;
        }
        if(builtInPOJOSet.contains(objClazz) || Collection.class.isAssignableFrom(objClazz)) {
            try {
                em.detach(obj);
            } catch (Exception ex) {
                // do nothing if we can't detach entity
            }
        }
        Field[] fields = objClazz.getDeclaredFields();
        Map<Field, Object> fieldValueMap = new HashMap<>();
        visitedNonBuiltInClazz.add(objClazz);
        for(Field field: fields) {
            field.setAccessible(true);
            Object value;
            String name = field.getName();
            Class<?> type = field.getType();
            if(visitedNonBuiltInClazz.contains(type)) {
                continue;
            }
            if(!builtInClazzSet.contains(type)) {
                if(!builtInPOJOSet.contains(type) && !(Collection.class.isAssignableFrom(type))) {
                    System.out.println("not build in pojo " + name);
                    continue;
                }
                value = field.get(obj);
                if(value == null) {
                    continue;
                }
                try {
                    if (value instanceof Collection<?>) {
                        Collection c = newCollection(type);
                        for (Object o : (Collection) value) {
                            if(!visitedNonBuiltInClazz.contains(o.getClass())) {
                                Object tmpV = getDeepFieldValueMap(o, visitedNonBuiltInClazz, fieldNameMap);
                                if(tmpV != null) {
                                    c.add(tmpV);
                                }
                            }
                        }
                        value = c;
                    } else {
                        value = getDeepFieldValueMap(value, visitedNonBuiltInClazz, fieldNameMap);
                    }
                } catch (Exception ex) {
                    /*
                        Problem : current entity is detached from jpa persistent context, reflection will hit method
                                  in lazy loading reference and throw LazyInitializationException.
                        Reason :  spring.jpa.open-in-view default setting is true, entitymanager per request
                                  if we don't use @transactional, entitymanager won't be closed before sending response to client side.
                                  so, any lazy loading methods would hit database through entitymanager and retrieve data.
                                  (Intellij debugger load lazy loading objects automatically)
                     */
                    continue;
                }
            } else {
                value = field.get(obj);
            }
            if(value == null) {
                continue;
            }
            fieldValueMap.put(field, value);
            if(!fieldNameMap.containsKey(name)) {
                fieldNameMap.put(name, new ArrayList<>());
            }
            if(builtInClazzSet.contains(value.getClass())) {
                fieldNameMap.get(name).add(value);
            } else {
                fieldNameMap.get(name).add(field);
            }

        }
        visitedNonBuiltInClazz.remove(objClazz);
        return fieldValueMap;
    }

    private Collection<?> newCollection(Class<?> type) throws NullPointerException{
        if(type.equals(Set.class)) {
            return new HashSet<>();
        } else if(type.equals(List.class)) {
            return new ArrayList<>();
        } else {
            return null;
        }
    }


    public static void addBuiltInPOJO(Class<?> pojoClass) {
        builtInPOJOSet.add(pojoClass);
    }
}

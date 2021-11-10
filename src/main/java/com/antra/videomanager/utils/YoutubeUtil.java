package com.antra.videomanager.utils;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.logging.Logger;

public class YoutubeUtil {

    private static final List<String> SCOPES = Arrays.asList(YouTubeScopes.YOUTUBE_READONLY, "playlistupdates");

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static YouTube youtube;

    private static final String propertyName = "client-secret.properties";


    private static void initYoutube() throws IOException, GeneralSecurityException {
        if(youtube == null) {
            synchronized (YoutubeUtil.class) {
                if(youtube == null) {
                    Credential credential = authorize();
                    youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName("application-sample").build();
                }
            }
        }
    }

    private static Set<String> getVideoIdFromMineChannel() throws IOException, GeneralSecurityException {
        initYoutube();
        String pageToken = "";
        Set<String> videos = new HashSet<>();
        while(pageToken != null) {
            SearchListResponse response = youtube.search().list("snippet")
                    //.setForMine(true)
                    .setType("video")
                    .setMaxResults(50L)
                    .setPageToken(pageToken)
                    .execute();
            pageToken = response.getNextPageToken();
            String[] videoIdJson = response.getItems().toString().split("\"videoId\":\"");
            for(int i = 1; i < videoIdJson.length; i++) {
                String videoId = videoIdJson[i].split("\"")[0];
                if(videoId != null && !videoId.equals(""))
                    videos.add(videoId);
            }
        }
        return videos;
    }

    private static Map<String, String> getPlaylistIdFromChannelId(String channelId) throws IOException, GeneralSecurityException {
        initYoutube();
        Map<String, String> playlistIdMap = new LinkedHashMap<>();
        List<String> playlistIdList = new ArrayList<>();
        PlaylistListResponse playlistListResponse = youtube.playlists()
                .list("snippet")
                .setChannelId(channelId)
                .execute();
        String[] playlistNames = playlistListResponse.getItems().toString().split("\"title\":\"");
        for(int i = 1; i < playlistNames.length; i++) {
            String name = playlistNames[i].split("\"")[0];
            if(name != null && !name.equals(""))
                playlistIdMap.put(name, null);
        }
        String[] playlistId = playlistListResponse.getItems().toString().split("\"id\":\"");
        for(int i = 1; i < playlistId.length; i++) {
            String id = playlistId[i].split("\"")[0];
            if(id != null && !id.equals(""))
                playlistIdList.add(id);
        }
        int index = 0;
        for(Map.Entry<String, String> entry: playlistIdMap.entrySet()) {
            playlistIdMap.put(entry.getKey(), playlistIdList.get(index++));
        }
        return playlistIdMap;
    }

    public static Set<String> getVideoIdFromPlayListItem(String playlistId) throws IOException, GeneralSecurityException {
        initYoutube();
        Set<String> set = new HashSet<>();
        String nextPageToken = "";
        while(nextPageToken != null) {
            PlaylistItemListResponse playlistListItemResponse = youtube.playlistItems().list("contentDetails")
                    .setPageToken(nextPageToken)
                    .setMaxResults(50L)
                    .setPlaylistId(playlistId)
                    .execute();
            nextPageToken = playlistListItemResponse.getNextPageToken();
            String[] videoIdJson = playlistListItemResponse.getItems().toString().split("\"videoId\":\"");
            for(int i = 1; i < videoIdJson.length; i++) {
                String videoId = videoIdJson[i].split("\"")[0];
                if(videoId != null && !videoId.equals(""))
                    set.add(videoId);
            }
        }
        return set;
    }

    private static String insertPlaylistItem(String playlistId, String videoId) throws IOException {

        // Define a resourceId that identifies the video being added to the
        // playlist.
        ResourceId resourceId = new ResourceId();
        resourceId.setKind("youtube#video");
        resourceId.setVideoId(videoId);

        // Set fields included in the playlistItem resource's "snippet" part.
        PlaylistItemSnippet playlistItemSnippet = new PlaylistItemSnippet();
        playlistItemSnippet.setTitle("First video in the test playlist");
        playlistItemSnippet.setPlaylistId(playlistId);
        playlistItemSnippet.setResourceId(resourceId);


        // Create the playlistItem resource and set its snippet to the
        // object created above.
        PlaylistItem playlistItem = new PlaylistItem();
        playlistItem.setSnippet(playlistItemSnippet);

        // Call the API to add the playlist item to the specified playlist.
        // In the API call, the first argument identifies the resource parts
        // that the API response should contain, and the second argument is
        // the playlist item being inserted.
        YouTube.PlaylistItems.Insert playlistItemsInsertCommand =
                youtube.playlistItems().insert("snippet,contentDetails", playlistItem);
        PlaylistItem returnedPlaylistItem = playlistItemsInsertCommand.execute();

        // Print data from the API response and return the new playlist
        // item's unique playlistItem ID.

        System.out.println("New PlaylistItem name: " + returnedPlaylistItem.getSnippet().getTitle());
        System.out.println(" - Video id: " + returnedPlaylistItem.getSnippet().getResourceId().getVideoId());
        System.out.println(" - Posted: " + returnedPlaylistItem.getSnippet().getPublishedAt());
        System.out.println(" - Channel: " + returnedPlaylistItem.getSnippet().getChannelId());
        return returnedPlaylistItem.getId();

    }

    private static GoogleClientSecrets.Details getGoogleClientSecretsDetails(Properties clientSecretProperty) {
        GoogleClientSecrets.Details details = new GoogleClientSecrets.Details();
        details.set("auth_uri", clientSecretProperty.get("auth_uri"));
        details.set("client_id", clientSecretProperty.get("client_id"));
        details.set("client_secret", clientSecretProperty.get("client_secret"));
        details.set("redirect_uris", Arrays.asList(((String)clientSecretProperty.get("redirect_uris")).split(",")));
        details.set("token_uri", clientSecretProperty.get("token_uri"));
        details.set("project_id", clientSecretProperty.get("project_id"));
        details.set("auth_provider_x509_cert_url", clientSecretProperty.get("auth_provider_x509_cert_url"));
        return details;
    }

    private static Credential authorize() throws IOException, GeneralSecurityException {
        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
        Properties clientSecretProperty = PropertyUtil.fetchProperties(propertyName);
        GoogleClientSecrets.Details details = getGoogleClientSecretsDetails(clientSecretProperty);
        clientSecrets.setInstalled(details);
        // Build flow and trigger user authorization request.

        java.io.File dataStoreDIR = new java.io.File(System.getProperty(clientSecretProperty.getProperty("refresh_token_file_path")), clientSecretProperty.getProperty("refresh_token_file_uri"));

        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(new FileDataStoreFactory(dataStoreDIR))
                        .setAccessType("offline")
                        .setScopes(Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl",
                                "https://www.googleapis.com/auth/yt-analytics-monetary.readonly",
                                "https://www.googleapis.com/auth/yt-analytics.readonly"))
                        .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().build();
        AuthorizationCodeInstalledApp app = new AuthorizationCodeRest(
                flow, receiver);

        return app.authorize("user");
    }

    private static class AuthorizationCodeRest extends AuthorizationCodeInstalledApp {

        private final AuthorizationCodeFlow flow;
        private final VerificationCodeReceiver receiver;
        private static final Logger LOGGER = Logger.getLogger(AuthorizationCodeInstalledApp.class.getName());

        public AuthorizationCodeRest(AuthorizationCodeFlow flow, VerificationCodeReceiver receiver) {
            super(flow, receiver);
            this.flow = Preconditions.checkNotNull(flow);
            this.receiver = Preconditions.checkNotNull(receiver);
        }

        public Credential authorize(String userId) throws IOException {
            Credential var7;
            try {
                Credential credential = this.flow.loadCredential(userId);
                if (credential != null && (credential.getRefreshToken() != null || credential.getExpiresInSeconds() == null || credential.getExpiresInSeconds().longValue() > 60L)) {
                    return credential;
                }
                String redirectUri = this.receiver.getRedirectUri();
                AuthorizationCodeRequestUrl authorizationUrl = this.flow.newAuthorizationUrl().setRedirectUri(redirectUri);
                //open server local browser
                this.onAuthorization(authorizationUrl);
                String code = this.receiver.waitForCode();
                TokenResponse response = this.flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
                var7 = this.flow.createAndStoreCredential(response, userId);
            } finally {
                this.receiver.stop();
            }

            return var7;
        }
    }
}

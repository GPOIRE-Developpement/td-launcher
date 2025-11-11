package tdlauncher.controller;

import org.json.JSONObject;
import tdlauncher.Launcher;
import tdlauncher.auth.SteamOpenId;
import tdlauncher.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Consumer;

public class LoginController {
    private final Launcher launcher;
    private static final String API_KEY = "CC6DA004B72C10F293E2FB84757308FA";

    public LoginController(Launcher launcher) {
        this.launcher = launcher;
    }

    public void loginWithSteam(Consumer<Long> onSuccess, Consumer<String> onError) {
        SteamOpenId openId = new SteamOpenId();

        openId.startLoginEmbedded(steamId -> {
            User player = this.getSteamUser(steamId);

            launcher.setCurrentUser(player);

            System.out.println("[LoginController] SteamID received: " + steamId);
            if (onSuccess != null)
                onSuccess.accept(steamId);

        }, err -> {
            System.err.println("[LoginController] Steam login error: " + err);
            if (onError != null)
                onError.accept(err);
        });
    }

    public static User getSteamUser(long steamId) {
        try {
            String url = String.format("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key=%s&steamids=%s", API_KEY, steamId);

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) response.append(line);
            in.close();

            JSONObject player = new JSONObject(response.toString()).getJSONObject("response").getJSONArray("players").getJSONObject(0);

            return new User(
                player.getLong("steamid"),
                player.getString("personaname"),
                player.getString("avatarfull"),
                "FR"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

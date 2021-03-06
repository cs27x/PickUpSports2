package pickupsports2.ridgewell.pickupsports2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import pickupsports2.ridgewell.pickupsports2.intents.IntentProtocol;
import ridgewell.pickupsports2.common.Badge;
import ridgewell.pickupsports2.common.Sport;
import ridgewell.pickupsports2.common.User;

/**
 * Created by cameronridgewell on 1/26/15.
 */
public class ViewUserActivity extends Activity {
    User user;

    public ViewUserActivity() {}

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_screen);

        this.user = IntentProtocol.getUser(this);

        setTitle(user.getUsername());

        TextView username = (TextView) findViewById(R.id.user_name);
        username.setText(user.getUsername());

        TextView joindate = (TextView) findViewById(R.id.user_join_date);
        joindate.setText(user.getJoinTime().toString());

        TextView badges = (TextView) findViewById(R.id.user_badges);
        String badges_list = "";
        for (Badge badge : user.getBadges()) {
            badges_list = badges_list + badge + " ";
        }
        badges.setText(badges_list);

        TextView user_favorite_sports = (TextView) findViewById(R.id.user_favorite_sports);
        String favorite_sports_list = "";
        for (Sport sport: user.getFavoriteSports()) {
            favorite_sports_list = favorite_sports_list + sport + " ";
        }
        user_favorite_sports.setText(favorite_sports_list);

        TextView user_location = (TextView) findViewById(R.id.user_location);
        user_location.setText(user.getLocation().getLocation());

    }
}

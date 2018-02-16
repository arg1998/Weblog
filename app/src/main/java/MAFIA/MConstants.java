package MAFIA;

/**
 * Created by ARG on 9/24/2017 - 8:52 PM
 */

public class MConstants
{
    public class Server
    {
        public static final String serverUrl = ""; //// TODO: 9/24/2017 add server ip address here

        public class Events
        {
            public class Send
            {
                public static final String signUp = "signup_s";
                public static final String logIn = "login_s";
            }

            public class Receive
            {
                public static final String signUp = "signup_r";
                public static final String logIn = "login_r";
            }
        }
    }

    public class Storage
    {
        public static final String PrefsUserData = "MafiaUserData";
        public static final String PrefsSetting = "MafiaUserData";
        public static final String PrefsSetting_SoundFx_KEY = "soundFx";
        public static final String PrefsSetting_Music_KEY = "music";
    }
}

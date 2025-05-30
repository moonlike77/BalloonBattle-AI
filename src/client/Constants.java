package client;

public class Constants {
    
    public class Config {
        public class Net {
            public final static String ip = "127.0.0.1";
            public final static int port = 9495;
        }
    }
    
    public static class Project {
        public final static String Title = "T-Project Client (942)";
        public class Version {
            public final static String Code = "1.0.0";
            public final static String Name = "Beginning";
        }
        public static String to_String() {
            return Title + " | " + "version: " + Version.Code + " [" + Version.Name + "]";
        }
    }
    
}

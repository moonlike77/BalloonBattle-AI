package Game;

public class Command {

    private static int counter = 0;
    
    private static final int _NONE = counter++;
    private static final int _UP = counter++;
    private static final int _RIGHT = counter++;
    private static final int _DOWN = counter++;
    private static final int _LEFT = counter++;
    private static final int _POP = counter++;
    private static final int _POOFF = counter++;

    public static final Command NONE = new Command(_NONE);
    public static final Command UP = new Command(_UP);
    public static final Command RIGHT = new Command(_RIGHT);
    public static final Command DOWN = new Command(_DOWN);
    public static final Command LEFT = new Command(_LEFT);
    public static final Command POP = new Command(_POP);
    public static final Command POOFF = new Command(_POOFF);

    private final int _cmd;

    private static final String _strings[] = new String[counter];

    static {
        _strings[_NONE] = "none";
        _strings[_UP] = "up";
        _strings[_RIGHT] = "right";
        _strings[_DOWN] = "down";
        _strings[_LEFT] = "left";
        _strings[_POP] = "pop";
        _strings[_POOFF] = "pooff";
    }

    private Command(int _cmd) {
        this._cmd = _cmd;
    }

    @Override
    public String toString() {
        if(_cmd >= 0 && _cmd < _strings.length) {
            return _strings[_cmd];
        }
        return null;
    }
    
}
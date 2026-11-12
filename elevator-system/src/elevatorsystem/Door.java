package elevatorsystem;

public class Door {
    private boolean open;
    public void open()
    {
        open = true;
    }
    public void close()
    {
        open = false;
    }
    public boolean isOpen()
    {
        return open;
    }
}

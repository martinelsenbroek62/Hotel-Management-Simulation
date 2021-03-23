package Managers;

public interface HteListener {
	
	public boolean registerManager(Icontrol manager);
	public boolean deregisterManager(Icontrol manager);
	public void notifyManager();

}

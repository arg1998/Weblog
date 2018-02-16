package MAFIA.Network.Interfaces;

/**
 * this interface is responsible for interfering the messages from server to current submitted Activity (context)
 * Each method will trigger respective methods , with state of the connection corresponding to server
 *
 * USAGE : this interface must be implemented in such Activities which communicate with server
 */
public interface ConnectionStateListener
{
    void onConnected();

    void onDisConnected(Object result);

    void onConnectingError(Object result);

    void onReconnecting(Object result);
}

package MAFIA.Network.Interfaces;

import org.json.JSONObject;

/**
 * this interface handles server Responses
 */

public interface OnServerResponse
{
    void onResponse(JSONObject response);
}

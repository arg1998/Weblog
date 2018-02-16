package MAFIA.Network;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ARG on 9/24/2017 - 9:12 PM
 */

public class Parser
{
    /**
     * this function creates a JSON Object from the Map< key , value>
     * @param values Key Value Pairs of JSON object
     */
    public static JSONObject toJsonObject(HashMap<String , String > values)
    {
        JSONObject jsonObject = new JSONObject();
        for (String key : values.keySet())
        {
            try
            {
                jsonObject.put(key , values.get(key));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return jsonObject;
    }

    public static JSONArray toJsonArray(ArrayList<HashMap<String , String>> array)
    {
        JSONArray jsonArray = new JSONArray();



        for (int i = 0 ; i < array.size() ; i++)
        {
            HashMap<String ,String> tempJson = array.get(i);
            JSONObject jsonObject = new JSONObject();

            for (String key : tempJson.keySet())
            {
                try
                {
                    jsonObject.put(key , tempJson.get(key));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}

package softagi.eqraa;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import softagi.eqraa.Models.BookModel;

public class Utils
{
    static String title,author,publisher,published_date,desc,thumbnail;
    static int page_count;
    static double rate;

    public static List<BookModel> utils (String api) throws Exception
    {
        URL url = createURL(api);
        String json = makeHTTPrequest(url);
        List<BookModel> bk = extractDatafromJSON(json);

        return bk;
    }

    public static URL createURL (String api) throws MalformedURLException
    {
        URL url = null;

        url = new URL(api);

        return url;
    }

    public static String makeHTTPrequest (URL url) throws IOException
    {
        String response = "";

        if (url == null)
        {
            return  response;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try
        {
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                response = readFromStream(inputStream);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }

            if (inputStream != null)
            {
                inputStream.close();
            }
        }

        return response;
    }

    public static String readFromStream (InputStream inputStream) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();

            while (line != null)
            {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }

        return stringBuilder.toString();
    }

    public static List<BookModel> extractDatafromJSON (String response) throws JSONException
    {
        if (response.isEmpty())
        {
            return null;
        }

        List<BookModel> bookModels = new ArrayList<>();

        JSONObject root = new JSONObject(response);
        JSONArray items = root.getJSONArray("items");

        for (int i = 0 ; i < items.length() ; i ++)
        {
            JSONObject each_item = items.getJSONObject(i);
            JSONObject vi = each_item.getJSONObject("volumeInfo");

            if (vi.has("title"))
            {
                title = vi.getString("title");
            } else
                {
                    title = "No Title Found";
                }

            if (vi.has("authors"))
            {
                author = vi.getJSONArray("authors").getString(0);
            } else
            {
                author = "No Author Found";
            }

            if (vi.has("publisher"))
            {
                publisher = vi.getString("publisher");
            } else
            {
                publisher = "No Publisher Found";
            }

            if (vi.has("publishedDate"))
            {
                published_date = vi.getString("publishedDate");
            } else
            {
                published_date = "No Date Found";
            }

            if (vi.has("description"))
            {
                desc = vi.getString("description");
            } else
            {
                desc = "No Description Found";
            }

            if (vi.has("pageCount"))
            {
                page_count = vi.getInt("pageCount");
            } else
            {
                page_count = 0;
            }

            if (vi.has("averageRating"))
            {
                rate = vi.getDouble("averageRating");
            } else
            {
                rate = 0.0;
            }

            if (vi.has("imageLinks"))
            {
                thumbnail = vi.getJSONObject("imageLinks").getString("thumbnail");
            } else
                {
                    thumbnail = "";
                }

            BookModel bookModel = new BookModel(title,author,publisher,published_date,desc,thumbnail,page_count,rate);

            bookModels.add(bookModel);
        }

        return bookModels;
    }
}

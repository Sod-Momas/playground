package cc.momas.java.demo.generic.complexobj;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huluxia-wc on 14-9-22.
 */
public class JsonHlx {
	private static Gson gson = new GsonBuilder().create();

	/**
	 * parse json string to object
	 *
	 * @param json
	 * @param clz
	 * @param <T>
	 * @return
	 * @throws java.io.IOException
	 */
	public static <T> T parseJsonObject(String json, Class<T> clz) {
		return gson.fromJson(json, clz);
	}

	/**
	 * parse json string to Array
	 *
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public static <T> T[] parseJsonArray(String json, Class<T> clz) {
		T[] result = gson.fromJson(json, new TypeToken<T[]>() {}.getType());
		return result;
	}

	/**
	 * parse json string to Map
	 *
	 * @param keyType
	 * @param valueType
	 * @return
	 * @throws Exception
	 */
	public static <K, V> Map<K, V> parseJsonMap(String json, Class<K> keyType,
	                                            Class<V> valueType) {
		Map<K, V> result = gson.fromJson(json, new TypeToken<Map<K, V>>() {}.getType());
		return result;
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	@SuppressWarnings("unused")
	private static class NumberTypeAdapter implements JsonSerializer<Number> {
		@Override
		public JsonElement serialize(Number src, Type typeOfSrc,
		                             JsonSerializationContext context) {
			return new JsonPrimitive(src);
		}
	}

	public static <T> List<T> parseJsonList(String json, Class<T> clz)
			throws Exception {
		com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		JsonElement element = parser.parse(json);
		JsonArray array = element.getAsJsonArray();
		List<T> data = new ArrayList<T>();
		for (JsonElement jo : array) {
			data.add(gson.fromJson(jo, clz));
		}
		return data;
	}
}

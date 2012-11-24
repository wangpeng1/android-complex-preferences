package br.com.kots.mob.complex.preferences;

import java.lang.reflect.Type;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ComplexPreferences {

	private static ComplexPreferences complexPreferences;
	private Context context;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private static Gson GSON = new Gson();
	Type typeOfObject = new TypeToken<Object>() {
	}.getType();

	private ComplexPreferences(Context context, String namePreferences, int mode) {
		this.context = context;
		if (namePreferences == null || namePreferences.equals("")) {
			namePreferences = "complex_preferences";
		}
		preferences = context.getSharedPreferences(namePreferences, mode);
		editor = preferences.edit();
	}

	public static ComplexPreferences getComplexPreferences(Context context,
			String namePreferences, int mode) {

		if (complexPreferences == null) {
			complexPreferences = new ComplexPreferences(context,
					namePreferences, mode);
		}

		return complexPreferences;
	}

	public void putObject(String key, Object object) {
		editor.putString(key, GSON.toJson(object));
	}

	public void commit() {
		editor.commit();
	}

	public <T> T getObject(String key, Class<T> a) {
		

		String gson = preferences.getString(key, null);
		if (gson == null) {
			return null;
		} else {
			return GSON.fromJson(gson, a);
		}
	}
		
	
}

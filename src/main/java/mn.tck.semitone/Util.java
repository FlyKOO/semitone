/*
 * Semitone - tuner, metronome, and piano for Android
 * Copyright (C) 2019  Andy Tockman <andy@tck.mn>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package mn.tck.semitone;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextPaint;
import android.support.v7.preference.PreferenceManager;

import java.util.Locale;

public class Util {

    final static String[] notenames = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    public static int maxTextSize(String text, int maxWidth) {
        TextPaint paint = new TextPaint();
        for (int textSize = 10;; ++textSize) {
            paint.setTextSize(textSize);
            if (paint.measureText(text) > maxWidth) return textSize - 1;
        }
    }

    @SuppressWarnings("deprecation")
    public static Context fixLocale(Context c) {
        String lang = PreferenceManager.getDefaultSharedPreferences(c)
            .getString("language", "");
        if (lang == "") return c;

        Locale loc = new Locale(lang);
        Locale.setDefault(loc);
        Resources res = c.getResources();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            conf.setLocale(loc);
            return c.createConfigurationContext(conf);
        } else {
            conf.locale = loc;
            res.updateConfiguration(conf, res.getDisplayMetrics());
            return c;
        }
    }

}

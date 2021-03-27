package com.Rai.studycenter.helpers.custom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceViewHolder;

import com.Rai.studycenter.R;

public class MyPreferenceCategory extends PreferenceCategory {
    public MyPreferenceCategory(Context context) {
        super(context);
    }

    public MyPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPreferenceCategory(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override public final void onBindViewHolder(final PreferenceViewHolder vh)
    { super.onBindViewHolder(vh);

    TextView txt = (TextView) vh.findViewById(android.R.id.title); txt.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
    }
}
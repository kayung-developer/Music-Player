package com.slogan.musicplayer.ui.activities.intro;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;
import com.slogan.musicplayer.R;

public class AppIntroActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonCtaVisible(true);
        setButtonNextVisible(false);
        setButtonBackVisible(false);

        setButtonCtaTintMode(BUTTON_CTA_TINT_MODE_TEXT);

        addSlide(new SimpleSlide.Builder()
                .title(R.string.main_activity_name)
                .description(R.string.welcome_to_music_app)
                .image(R.mipmap.mp_foreground)
                .background(R.color.md_grey_300)
                .backgroundDark(R.color.md_grey_700)
                .layout(R.layout.fragment_simple_slide_large_image)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title(R.string.slogantech)
                .description(R.string.welcome_slogan)
                .image(R.mipmap.slogan_foreground)
                .background(R.color.md_grey_300)
                .backgroundDark(R.color.md_grey_700)
                .layout(R.layout.fragment_simple_slide_large_image)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title(R.string.label_view_playing_queue)
                .description(R.string.open_playing_queue_instruction)
                .image(R.drawable.tutorial_queue_swipe_up)
                .background(R.color.md_teal_500)
                .backgroundDark(R.color.md_teal_700)
                .layout(R.layout.fragment_simple_slide_large_image)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title(R.string.label_edit_playing_queue)
                .description(R.string.rearrange_playing_queue_instruction)
                .image(R.drawable.tutorial_rearrange_queue)
                .background(R.color.md_indigo_700)
                .backgroundDark(R.color.md_indigo_900)
                .layout(R.layout.fragment_simple_slide_large_image)
                .build());
    }
}

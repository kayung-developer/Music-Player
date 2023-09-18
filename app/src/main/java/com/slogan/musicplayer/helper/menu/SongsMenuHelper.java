package com.slogan.musicplayer.helper.menu;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.slogan.musicplayer.R;
import com.slogan.musicplayer.dialogs.AddToPlaylistDialog;
import com.slogan.musicplayer.dialogs.DeleteSongsDialog;
import com.slogan.musicplayer.helper.MusicPlayerRemote;
import com.slogan.musicplayer.model.Song;

import java.util.List;

public class SongsMenuHelper {
    public static boolean handleMenuClick(@NonNull FragmentActivity activity, @NonNull List<Song> songs, int menuItemId) {
        switch (menuItemId) {
            case R.id.action_play_next:
                MusicPlayerRemote.playNext(songs);
                return true;
            case R.id.action_add_to_current_playing:
                MusicPlayerRemote.enqueue(songs);
                return true;
            case R.id.action_add_to_playlist:
                AddToPlaylistDialog.create(songs).show(activity.getSupportFragmentManager(), "ADD_PLAYLIST");
                return true;
            case R.id.action_delete_from_device:
                DeleteSongsDialog.create(songs).show(activity.getSupportFragmentManager(), "DELETE_SONGS");
                return true;
        }
        return false;
    }
}

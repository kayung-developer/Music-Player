package com.slogan.musicplayer.ui.fragments.mainactivity.library.pager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.slogan.musicplayer.R;
import com.slogan.musicplayer.adapter.PlaylistAdapter;
import com.slogan.musicplayer.interfaces.LoaderIds;
import com.slogan.musicplayer.loader.PlaylistLoader;
import com.slogan.musicplayer.misc.WrappedAsyncTaskLoader;
import com.slogan.musicplayer.model.Playlist;
import com.slogan.musicplayer.model.smartplaylist.LastAddedPlaylist;
import com.slogan.musicplayer.model.smartplaylist.MyTopSongsPlaylist;
import com.slogan.musicplayer.model.smartplaylist.RecentlyPlayedPlaylist;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsFragment extends AbsLibraryPagerRecyclerViewFragment<PlaylistAdapter, LinearLayoutManager> implements LoaderManager.LoaderCallbacks<List<Playlist>> {

    private static final int LOADER_ID = LoaderIds.PLAYLISTS_FRAGMENT;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @NonNull
    @Override
    protected LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @NonNull
    @Override
    protected PlaylistAdapter createAdapter() {
        List<Playlist> dataSet = getAdapter() == null ? new ArrayList<>() : getAdapter().getDataSet();
        return new PlaylistAdapter(getLibraryFragment().getMainActivity(), dataSet, R.layout.item_list_single_row, getLibraryFragment());
    }

    @Override
    protected int getEmptyMessage() {
        return R.string.no_playlists;
    }

    @Override
    public void onMediaStoreChanged() {
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Playlist>> onCreateLoader(int id, Bundle args) {
        return new AsyncPlaylistLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Playlist>> loader, List<Playlist> data) {
        getAdapter().swapDataSet(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Playlist>> loader) {
        getAdapter().swapDataSet(new ArrayList<>());
    }

    private static class AsyncPlaylistLoader extends WrappedAsyncTaskLoader<List<Playlist>> {
        public AsyncPlaylistLoader(Context context) {
            super(context);
        }

        private static List<Playlist> getAllPlaylists(Context context) {
            List<Playlist> playlists = new ArrayList<>();

            playlists.add(new LastAddedPlaylist(context));
            playlists.add(new RecentlyPlayedPlaylist(context));
            playlists.add(new MyTopSongsPlaylist(context));

            playlists.addAll(PlaylistLoader.getAllPlaylists(context));

            return playlists;
        }

        @Override
        public List<Playlist> loadInBackground() {
            return getAllPlaylists(getContext());
        }
    }
}

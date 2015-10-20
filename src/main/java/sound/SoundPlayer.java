package sound;

import java.util.ArrayList;

import logic.Logger;

import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;

/**
 * Class to play music.
 * @author Bart
 *
 */
public final class SoundPlayer implements MusicListener {

	private Music activeMusic;
	private ArrayList<Music> menuList;
	private ArrayList<Music> playList;
	private ArrayList<Music> wonList;
	private ArrayList<Music> lostList;
	private int currentMusic;
	private static volatile SoundPlayer instance;
	private boolean playingMusic;
	private MusicLists activeList;
	private boolean testing;
	
	private static final int FADE_LENGTH = 20000;
	
	  /**
     * The different powerup types.
     */
    public enum MusicLists {
    	MENU_LIST, PLAY_LIST, WON_LIST, LOST_LIST;
    	
    }

    /**
	 * Private constructor (singleton).
	 */
	private SoundPlayer() {
		initMenuList();
		initPlayList();
		initWonList();
		initLostList();
		testing = true;
		
		activeList = MusicLists.MENU_LIST;
		playingMusic = false;
		currentMusic = 0;
		activeMusic = getActiveList().get(currentMusic);
	}
	
	/**
	 * Return active musiclist based on activeList.
	 * @return	the active musiclist
	 */
	private ArrayList<Music> getActiveList() {
		switch (activeList) {
		case MENU_LIST:
			return menuList;
		case PLAY_LIST:
			return playList;
		case WON_LIST:
			return wonList;
		case LOST_LIST:
			return lostList;
		default:
			return null;
		}
	}
	
	/**
	 * Set the active musiclist.
	 * @param list	the active list.
	 */
	public void setActiveList(MusicLists list) {
		if (list != activeList) {
			activeList = list;
			currentMusic = 0;
			
			activeMusic.stop();
			activeMusic = getActiveList().get(currentMusic);
			
			if (playingMusic) {
				if (activeList == MusicLists.PLAY_LIST) {
					activeMusic.play(1.0f, 0.0f);
					activeMusic.fade(FADE_LENGTH, 1.0f, false);
				} else {
					activeMusic.play();					
				}
			}
		}
	}
	
	/**
	 * Init the menuList.
	 */
	private void initMenuList() {
		menuList = new ArrayList<Music>();
		try {
			menuList.add(new Music("resources/sound/menu_music.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Init the playList.
	 */
	private void initPlayList() {
		playList = new ArrayList<Music>();
		try {
			playList.add(new Music("resources/sound/play_music_2.ogg"));
			playList.add(new Music("resources/sound/play_music.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Init the wonList.
	 */
	private void initWonList() {
		wonList = new ArrayList<Music>();
		try {
			wonList.add(new Music("resources/sound/win_music.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Init the lostList.
	 */
	private void initLostList() {
		lostList = new ArrayList<Music>();
		try {
			lostList.add(new Music("resources/sound/lost_music.ogg"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Return the Logger instance.
	 * @return the logger
	 */
	public static SoundPlayer getInstance() {
		if (instance == null) {
			synchronized (Logger.class) {
				if (instance == null) {
					instance = new SoundPlayer();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Start playing music.
	 */
	public void play() {
		if (!playingMusic && !testing) {
			activeMusic.play();
			playingMusic = true;
		}
	}

	/**
	 * pause music.
	 */
	public void pause() {
		if (playingMusic) {
			activeMusic.pause();
			playingMusic = false;
		}
	}
	
	
	@Override
	public void musicEnded(Music arg0) {
		currentMusic++;
		
		if (currentMusic >= getActiveList().size()) {
			currentMusic = 0;
		}
		
		activeMusic = getActiveList().get(currentMusic);
		
	}

	@Override
	public void musicSwapped(Music arg0, Music arg1) {
	}

	/**
	 * @param testing the testing to set
	 */
	public void setTesting(boolean testing) {
		this.testing = testing;
	}
	
	

}

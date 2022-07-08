package postmanClone.UI.Frames;

import java.util.ArrayList;
import java.util.List;

public class MainFrameRecordsHandler {

	private static final List<MainFrameRecordsListener> listeners = new ArrayList<>();

	public static void addListener(MainFrameRecordsListener listener) {
		listeners.add(listener);
	}
	
	public static void bookmarksListChanged() {
		for (MainFrameRecordsListener listener : listeners)
			listener.bookmarkSaved();
	}
	
	public static void historiesListChanged() {
		for (MainFrameRecordsListener listener : listeners)
			listener.historySaved();
	}
}

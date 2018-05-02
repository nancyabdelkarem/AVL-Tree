package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary<T extends Comparable<T>> implements IDictionary {
	AVLTree<T> avl = new AVLTree<T>();

	@Override
	public void load(final File file) {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				if (!avl.search((T) line)) {
					avl.insert((T) line);
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean insert(final String word) {
		if (!avl.search((T) word)) {
			avl.insert((T) word);
			return true;
		}
		return false;
	}

	@Override
	public boolean exists(final String word) {

		return avl.search((T) word);
	}

	@Override
	public boolean delete(final String word) {
		return avl.delete((T) word);
	}

	@Override
	public int size() {

		return avl.size();
	}

	@Override
	public int height() {

		return avl.height();
	}

}

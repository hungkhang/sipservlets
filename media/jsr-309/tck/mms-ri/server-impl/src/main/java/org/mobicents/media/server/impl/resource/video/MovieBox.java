/*
 * Mobicents, Communications Middleware
 * 
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party
 * contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 *
 * Boston, MA  02110-1301  USA
 */

package org.mobicents.media.server.impl.resource.video;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>8.2.1.1 Definition</b>
 * <ul>
 * <li>Box Type: ‘moov’</li>
 * <li>Container: File</li>
 * <li>Mandatory: Yes</li>
 * <li>Quantity: Exactly one</li>
 * </ul>
 * <p>
 * The metadata for a presentation is stored in the single Movie Box which occurs at the top-level of a file. Normally
 * this box is close to the beginning or end of the file, though this is not required.
 * </p>
 * 
 * @author kulikov
 * @author amit bhayani
 */
public class MovieBox extends Box {

	// File Type = moov
	static byte[] TYPE = new byte[] { AsciiTable.ALPHA_m, AsciiTable.ALPHA_o, AsciiTable.ALPHA_o, AsciiTable.ALPHA_v };
	static String TYPE_S = "moov";
	static {
		bytetoTypeMap.put(TYPE, TYPE_S);
	}

	private MovieHeaderBox movieHeaderBox;
	private List<TrackBox> trackBoxes = new ArrayList<TrackBox>();
	private TrackBox track;
	private UserDataBox userDataBox = null;

	public MovieBox(long size) {
		super(size, TYPE_S);
	}

	@Override
	protected int load(DataInputStream in) throws IOException {

		int count = 8;

		// loading movie header
		long len = readU32(in);
		byte[] type = read(in);

		// First MovieHeaderBox
		if (!comparebytes(type, MovieHeaderBox.TYPE)) {
			throw new IOException("Movie Header Box expected");
		}

		movieHeaderBox = new MovieHeaderBox(len);
		count += movieHeaderBox.load(in);

		// Next multiple TrackBox
		while (count < getSize()) {
			// loading track
			len = readU32(in);
			type = read(in);

			if (comparebytes(type, TrackBox.TYPE)) {
				track = new TrackBox(len);
				count += track.load(in);
				trackBoxes.add(track);
			} else if (comparebytes(type, UserDataBox.TYPE)) {
				userDataBox = new UserDataBox(len, false);
				count += userDataBox.load(in);
			} else {
				throw new IOException("Track box expected. But is " + (new String(type)));
			}

		}

		return (int) this.getSize();
	}

	public MovieHeaderBox getMovieHeaderBox() {
		return movieHeaderBox;
	}

	public List<TrackBox> getTrackBoxes() {
		return trackBoxes;
	}

}
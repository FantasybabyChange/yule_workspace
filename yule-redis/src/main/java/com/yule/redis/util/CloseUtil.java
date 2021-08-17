package com.yule.redis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CloseUtil {

	public static void close(Object o) throws IOException {
		if (o instanceof ByteArrayInputStream) {
			((ByteArrayInputStream) o).close();
		} else if (o instanceof ByteArrayOutputStream) {
			((ByteArrayOutputStream) o).close();
		} else if (o instanceof ObjectInputStream) {
			((ObjectInputStream) o).close();
		} else if (o instanceof ObjectOutputStream) {
			((ObjectOutputStream) o).close();
		}
	}

	public static void close(Closeable closeable) throws IOException {
		if (closeable != null) {
			closeable.close();
		}
	}

}

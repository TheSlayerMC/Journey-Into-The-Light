package ru.timeconqueror.timecore.util;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class ResourceUtils {
    /**
     * Returns the URL of resource in current jar file with given {@code path}.
     * Returns null, if there is no resource with given {@code path}.
     */
    public static URL getURL(String path) {
        return ResourceUtils.class.getClassLoader().getResource(path);
    }

    /**
     * Returns the InputStream of resource in current jar file with given {@code path}.
     * Returns null, if there is no resource with given {@code path}.
     */
    public static InputStream getStream(String path) {
        return ResourceUtils.class.getClassLoader().getResourceAsStream(path);
    }

    /**
     * Returns the CharSource of resource in current jar file with given {@code path} at UTF-8 encoding.
     * Returns null, if there is no resource with given {@code path}.
     */
    public static CharSource asCharSource(String path) {
        return asCharSource(path, Charsets.UTF_8);
    }

    /**
     * Returns the CharSource of resource in current jar file with given {@code path} at UTF-8 encoding.
     * Returns null, if there is no resource with given {@code path}.
     */
    @SuppressWarnings("UnstableApiUsage")
    public static CharSource asCharSource(String path, Charset charset) {
        URL resourceURL = getURL(path);
        return resourceURL != null ? Resources.asCharSource(resourceURL, charset) : null;
    }

    /**
     * Returns string representation of path to resource inside current jar file, which is packed in data/ folder.
     *
     * @param path path inside data/ folder.
     */
    public static String asDataSubpath(String path) {
        return resolvePath("data", path);
    }

    /**
     * Returns string representation of path to resource inside current jar file, which is packed in assets/ folder.
     *
     * @param path path inside assets/ folder.
     */
    public static String asAssetsSubpath(String path) {
        return resolvePath("assets", path);
    }

    /**
     * Combines head and appendix string paths.
     */
    public static String resolvePath(String head, String appendix) {
        if (head.endsWith("/") || head.endsWith("\\")) {
            head = head.substring(0, head.length() - 1);
        }

        if (appendix.startsWith("/") || appendix.startsWith("\\")) {
            appendix = appendix.substring(1);
        }

        return head + "/" + appendix;
    }
}

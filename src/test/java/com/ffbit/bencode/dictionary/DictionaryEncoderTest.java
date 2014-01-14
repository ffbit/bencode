package com.ffbit.bencode.dictionary;

import com.ffbit.bencode.BEncoder;
import com.ffbit.bencode.Encoder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class DictionaryEncoderTest {
    private Encoder encoder;
    private OutputStream out;
    private Map<String, ? super Object> dictionary;

    @Before
    public void setUp() throws Exception {
        out = new ByteArrayOutputStream();
        encoder = new DictionaryEncoder(new BEncoder(out));
    }

    @Test
    public void itShouldEncodeEmptyDictionary() throws Exception {
        dictionary = Collections.emptyMap();

        assertThat(encoder.encode(dictionary), is("de"));
    }

    @Test
    public void itShouldEncodeDictionaryOfStringAndInteger() throws Exception {
        dictionary = Collections.<String, Object>singletonMap("answer", 42);

        assertThat(encoder.encode(dictionary), is("d6:answeri42ee"));
    }

    @Test
    public void itShouldEncodeDictionarySortedByKeysAlphabetically() throws Exception {
        dictionary = new LinkedHashMap<String, Object>();
        dictionary.put("z", "end");
        dictionary.put("a", "beginning");

        assertThat(encoder.encode(dictionary), is("d1:a9:beginning1:z3:ende"));
    }

}

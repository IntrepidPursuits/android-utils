package io.intrepid.commonutils.util;

import android.text.SpannableStringBuilder;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyInt;

/**
 * Mocks a {@link SpannableStringBuilder} and implements some of its functionality through a {@link String}.
 *
 * Just call {@link #setup()} in the test's setup method to be able to use this mocked class.
 *
 * Created by Anton Spaans on 5/6/16.
 */
public class MockedStringSpannableStringBuilder {
    public static void setup() {
        try {
            PowerMockito.whenNew(SpannableStringBuilder.class).withAnyArguments().then(new Answer<SpannableStringBuilder>() {
                @Override
                public SpannableStringBuilder answer(InvocationOnMock invocation) throws Throwable {
                    Object[] arguments = invocation.getArguments();
                    MockedStringSpannableStringBuilder mockedBuilder = new MockedStringSpannableStringBuilder(arguments != null && arguments.length > 0 ? (String) arguments[0] : "");
                    return mockedBuilder.spannableStringBuilder;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String mValue;
    private final SpannableStringBuilder spannableStringBuilder;

    private MockedStringSpannableStringBuilder() {
        spannableStringBuilder = Mockito.mock(SpannableStringBuilder.class);
        mValue = "";

        Mockito.when(spannableStringBuilder.append(any(CharSequence.class))).then(new Answer<SpannableStringBuilder>() {
            @Override
            public SpannableStringBuilder answer(InvocationOnMock invocation) throws Throwable {
                mValue = mValue + invocation.getArguments()[0].toString();
                return spannableStringBuilder;
            }
        });

        Mockito.when(spannableStringBuilder.append(anyChar())).then(new Answer<SpannableStringBuilder>() {
            @Override
            public SpannableStringBuilder answer(InvocationOnMock invocation) throws Throwable {
                mValue = mValue + invocation.getArguments()[0].toString();
                return spannableStringBuilder;
            }
        });

        Mockito.when(spannableStringBuilder.length()).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return mValue.length();
            }
        });

        Mockito.when(spannableStringBuilder.charAt(anyInt())).then(new Answer<Character>() {
            @Override
            public Character answer(InvocationOnMock invocation) throws Throwable {
                return mValue.charAt(((Number) invocation.getArguments()[0]).intValue());
            }
        });
    }

    private MockedStringSpannableStringBuilder(String string) {
        this();
        mValue = string;
    }
}

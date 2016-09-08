package io.intrepid.commonutils.util;

import android.text.TextUtils;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.Matchers.any;

/**
 * Mocks the {@link TextUtils} class.
 *
 * Created by aspaans on 5/6/16.
 */
public class MockedTextUtils {
    public static void setup() {
        PowerMockito.mockStatic(TextUtils.class);
        Mockito.when(TextUtils.isEmpty(any(CharSequence.class))).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence arg = invocation.getArgumentAt(0, CharSequence.class);
                return (arg == null) || (arg.length() == 0);
            }
        });
    }
}

//Copyright (C) 2014-2015 Vertanzil . All rights reserved.
//This software is copyrighted work. Copying or reproducing the
//Software to any other server or location for further reproduction
//or redistribution is prohibited, unless such reproduction or
//redistribution is permitted by a license agreement accompanying
//such Software. You may not create derivative works of the Software,
//or attempt to decompile or reverse-engineer the Software unless
//otherwise permitted by law. Use of the Software is subject to
//the license terms of any license agreement that may accompany
//or is provided with the Software.
//Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
//CONDITIONS OF ANY KIND, either express or implied.
package com.source.java.announcer;


@SuppressWarnings("WeakerAccess")
public class Utils_Chat {
    private static final char ESCAPE = '\u00A7';
    public static String replaceColors(String text) {
        char[] chrarray = text.toCharArray();
        for (int index = 0; index < chrarray.length; index++) {
            char chr = chrarray[index];
            if (chr != '&') {
                continue;
            }
            if ((index + 1) == chrarray.length) {
                break;
            }
            char forward = chrarray[index + 1];

            if ((forward >= '0' && forward <= '9') || (forward >= 'a' && forward <= 'f') || (forward >= 'k' && forward <= 'r')) {
                chrarray[index] = ESCAPE;
            }
        }
        return new String(chrarray);
    }
}
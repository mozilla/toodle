/* -*- Mode: Java; c-basic-offset: 4; tab-width: 20; indent-tabs-mode: nil; -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.mozilla.toodle.rust;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.NativeLongByReference;

public interface JNA extends Library {
    String JNA_LIBRARY_NAME = "toodle_ffi";
    NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(JNA_LIBRARY_NAME);

    JNA INSTANCE = (JNA) Native.loadLibrary(JNA_LIBRARY_NAME, JNA.class);

    Pointer new_toodle(String dbPath);
    void toodle_destroy(Pointer toodle);

    void toodle_create_item(Pointer listManager, String name, NativeLongByReference dueDate);
    void toodle_update_item_by_uuid(Pointer listManager, String uuid, String name, NativeLongByReference dueDate, NativeLongByReference completionDate);
    void toodle_all_items(Pointer listManager, NativeItemsCallback callback);
    void item_c_destroy(Pointer item);

    void store_register_observer(Pointer Store, String key, Pointer attributes, int len, NativeTxObserverCallback callback );
    void store_unregister_observer(Pointer Store, String key);
    long store_entid_for_attribute(Pointer Store, String attr);

    NativeResult toodle_sync(Pointer toodle, String userUuid, String serverUri);
}

package com.juul.kable

import com.benasher44.uuid.Uuid
import platform.CoreBluetooth.CBCharacteristic
import platform.CoreBluetooth.CBDescriptor

public actual class Characteristic(
    internal val cbCharacteristic: CBCharacteristic,
) {

    public actual val uuid: Uuid by lazy { cbCharacteristic.UUID.toUuid() }

    public actual val descriptors: List<Descriptor>
        get() = cbCharacteristic.descriptors?.map {
            val cbDescriptor = it as CBDescriptor
            Descriptor(cbDescriptor.UUID.toUuid())
        } ?: emptyList()
}

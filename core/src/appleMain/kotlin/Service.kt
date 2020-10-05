package com.juul.kable

import com.benasher44.uuid.Uuid
import platform.CoreBluetooth.CBCharacteristic
import platform.CoreBluetooth.CBPeripheral
import platform.CoreBluetooth.CBService
import platform.CoreBluetooth.CBUUID

public actual class Service internal constructor(
    internal val cbService: CBService
) {

    public actual val uuid: Uuid
        get() = cbService.UUID.toUuid()

    public actual val characteristics: List<Characteristic>
        get() = cbService.characteristics?.map {
            val cbCharacteristic = it as CBCharacteristic
            Characteristic(cbCharacteristic)
        } ?: emptyList()
}

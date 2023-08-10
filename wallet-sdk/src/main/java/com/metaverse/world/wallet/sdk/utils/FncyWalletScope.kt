package com.metaverse.world.wallet.sdk.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

private val job = SupervisorJob()

@JvmSynthetic
internal var scope = CoroutineScope(job + Dispatchers.IO)
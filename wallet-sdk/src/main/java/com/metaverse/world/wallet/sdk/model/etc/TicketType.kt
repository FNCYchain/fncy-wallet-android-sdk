package com.metaverse.world.wallet.sdk.model.etc

enum class TicketType(val value: String) {
    AssetTransfer("SIGNATURE_TYPE_FOR_ASSET_TRANSFER"),
    SmartContract("SIGNATURE_TYPE_FOR_SMARTCONTRACT_EXECUTION"),
    WalletConnect("SIGNATURE_TYPE_FOR_WALLET_CONNECT")
}
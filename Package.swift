// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "ColorMeterPlugin",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "ColorMeterPlugin",
            targets: ["ReaderInterfacePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0"),
        .package(url: "https://github.com/Nando3327/ColorMeterKit.git", from: "1.0.0")
    ],
    targets: [
        .target(
            name: "ReaderInterfacePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm"),
                "ColorMeterKit"
            ],
            path: "ios/Sources/ReaderInterfacePlugin"),
        .testTarget(
            name: "ReaderInterfacePluginTests",
            dependencies: ["ReaderInterfacePlugin"],
            path: "ios/Tests/ReaderInterfacePluginTests")
    ]
)

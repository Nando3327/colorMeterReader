import Foundation

@objc public class ReaderInterface: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}

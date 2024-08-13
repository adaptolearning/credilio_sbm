import Flutter
import UIKit
// import SbmViewController
public class CrediliosbmPlugin: NSObject, FlutterPlugin {
    public static func register(with registrar: FlutterPluginRegistrar) {
        let channel = FlutterMethodChannel(name: "crediliosbm", binaryMessenger: registrar.messenger())
        let instance = CrediliosbmPlugin()
        registrar.addMethodCallDelegate(instance, channel: channel)
    }

    public func handle(_ call: FlutterMethodCall, result: @escaping  FlutterResult) {
        switch call.method {
        case "openLibrary":
            if let arguments = call.arguments as? [String: Any],
                let token = arguments["token"] as? String, 
                let url = arguments["url"] as? String {
                 let sbmViewController = SbmViewController()
                 sbmViewController.token = token
                 sbmViewController.url = url
                
                if let viewController = UIApplication.shared.keyWindow?.rootViewController {
                     viewController.present(sbmViewController, animated: true, completion: nil)
                    result("Opening SDK screen for endpoint")
                } else {
                    result(FlutterError(code: "VIEW_CONTROLLER_ERROR", message: "Unable to retrieve root view controller", details: nil))
                }               
            } else {
                result(FlutterError(code: "PARAMS_ERROR", message: "Token or Url parameter missing", details: nil))
            }
        default:
            result(FlutterMethodNotImplemented)
        }
    }
}

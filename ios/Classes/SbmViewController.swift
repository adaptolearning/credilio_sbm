import UIKit

class SbmViewController: UIViewController {

    var token: String?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Set up UI
        view.backgroundColor = .white

        // Example: Handle token if received
        if let token = token {
            print("Token received: \(token)")
            
            // Example: Initialize and use PartnerLibrary or perform specific actions
            PartnerLibrarySingleton.shared().init("https://sbmsmartbankinguat.esbeeyem.com:9443")
            let library = PartnerLibrarySingleton.shared().instance()
            
            // Example: Call SDK method using the token
            do {
                try library.open(token: token, path: "/banking/sbm/credit_card/SCC/landing") { resultCode in
                    // Handle SDK result if needed
                    print("SDK Result: \(resultCode)")
                }
            } catch {
                print("SDK invocation failed: \(error.localizedDescription)")
                // Optionally, handle error gracefully
            }
        }
    }

    // Implement any other necessary methods or functionality here
}

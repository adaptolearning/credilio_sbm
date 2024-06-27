import UIKit

class SbmViewController: UIViewController {

    var token: String?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Example: Create UI components or perform necessary setup
        view.backgroundColor = .white

        if let token = token {
            // Example: Use the token to perform any specific actions
            print("Token received: \(token)")
        }
    }

    // Implement any other necessary methods or functionality here
}

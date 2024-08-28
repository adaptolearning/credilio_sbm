import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'credilio_sbm_platform_interface.dart';

/// An implementation of [CredilioSbmPlatform] that uses method channels.
class MethodChannelCredilioSbm extends CredilioSbmPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('credilio_sbm');

  @override
  Future<String> openLibrary({
    required token,
    required String url,
  }) async {
    try {
      final result = await methodChannel.invokeMethod('openLibrary', {
        'token': token,
        'url': url,
      });
      debugPrint('SDK operation result: $result');
      return result;
    } on PlatformException catch (e) {
      debugPrint('Error: ${e.message}');
      return 'Error: ${e.message}';
    }
  }
}

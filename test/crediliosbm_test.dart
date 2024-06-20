import 'package:flutter_test/flutter_test.dart';
import 'package:crediliosbm/crediliosbm.dart';
import 'package:crediliosbm/crediliosbm_platform_interface.dart';
import 'package:crediliosbm/crediliosbm_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockCrediliosbmPlatform
    with MockPlatformInterfaceMixin
    implements CrediliosbmPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final CrediliosbmPlatform initialPlatform = CrediliosbmPlatform.instance;

  test('$MethodChannelCrediliosbm is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelCrediliosbm>());
  });

  test('getPlatformVersion', () async {
    Crediliosbm crediliosbmPlugin = Crediliosbm();
    MockCrediliosbmPlatform fakePlatform = MockCrediliosbmPlatform();
    CrediliosbmPlatform.instance = fakePlatform;

    expect(await crediliosbmPlugin.getPlatformVersion(), '42');
  });
}

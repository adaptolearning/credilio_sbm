import 'package:crediliosbm/crediliosbm.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final _crediliosbmPlugin = Crediliosbm();

  @override
  void initState() {
    super.initState();
  }

  Future<void> _openLibrary() async {
    try {
      await _crediliosbmPlugin.openLibrary(
          token: 'Your Token', email: 'Your email');
    } catch (e) {
      debugPrint('Error: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Plugin example app'),
      ),
      body: GestureDetector(
        onTap: _openLibrary,
        child: const Center(
          child: Text('Click Here'),
        ),
      ),
    );
  }
}

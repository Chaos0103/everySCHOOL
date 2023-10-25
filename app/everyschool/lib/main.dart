import 'package:everyschool/pages/home/home.dart';
import 'package:everyschool/pages/main/btmnav.dart';
import 'package:everyschool/pages/main/splash.dart';
import 'package:flutter/material.dart';
// import 'package:flutter/services.dart';

void main() {
  // SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle(
  //     statusBarColor: Colors.transparent,
  // ));
  runApp(MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        fontFamily: "Pretendard"
      ),
      home : Splash())
  );
}

class Main extends StatefulWidget {
  const Main({super.key});

  @override
  State<Main> createState() => _MainState();
}

class _MainState extends State<Main> {


  int selectedIndex = 2;
  void onItemTapped(int index) {
    setState(() {
      selectedIndex = index;
    });
  }

  final List<Widget> pages = [
    Center(child: Text('신고')),
    Center(child: Text('연락처')),
    Home(),
    Center(child: Text('메세지')),
    Center(child: Text('커뮤니티')),
  ];


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: pages[selectedIndex],
        bottomNavigationBar: SizedBox(
          height: 70,
          child: BtmNav(selectedIndex : selectedIndex, onItemTapped : onItemTapped)
        ),
    );
  }
}

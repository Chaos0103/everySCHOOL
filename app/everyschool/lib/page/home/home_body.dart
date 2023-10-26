import 'package:everyschool/page/home/community_new.dart';
import 'package:everyschool/page/home/menu_buttons.dart';
import 'package:everyschool/page/home/school_noti.dart';
import 'package:everyschool/page/home/user_info.dart';
import 'package:flutter/material.dart';

class HomeBody extends StatefulWidget {
  const HomeBody({super.key});

  @override
  State<HomeBody> createState() => _HomeBodyState();
}

class _HomeBodyState extends State<HomeBody> {
  @override
  Widget build(BuildContext context) {
    return CustomScrollView(
      slivers: [
        SliverToBoxAdapter(
          child: Image.asset('assets/images/home/banner.png'),
        ),
        SliverToBoxAdapter(
          child: Container(
              margin: EdgeInsets.fromLTRB(20, 15, 20, 15), child: SchoolInfo()),
        ),
        SliverToBoxAdapter(
          child: Container(
              // height: 60,
              margin: EdgeInsets.fromLTRB(0, 0, 0, 20),
              child: MenuButtons()),
        ),
        SliverToBoxAdapter(
          child: SchoolNoti(),
        ),
        SliverToBoxAdapter(
          child: CommunityNew(),
        )
      ],
    );
  }
}

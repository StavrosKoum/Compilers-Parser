
@.TreeVisitor_vtable = global [0 x i8*] []
@.TV_vtable = global [1 x i8*] [i8* bitcast (i32 (i8*)* @TV.Start to i8*)]
@.Tree_vtable = global [21 x i8*] [i8* bitcast (i1 (i8*,i32)* @Tree.Init to i8*), i8* bitcast (i1 (i8*,i8*)* @Tree.SetRight to i8*), i8* bitcast (i1 (i8*,i8*)* @Tree.SetLeft to i8*), i8* bitcast (i8* (i8*)* @Tree.GetRight to i8*), i8* bitcast (i8* (i8*)* @Tree.GetLeft to i8*), i8* bitcast (i32 (i8*)* @Tree.GetKey to i8*), i8* bitcast (i1 (i8*,i32)* @Tree.SetKey to i8*), i8* bitcast (i1 (i8*)* @Tree.GetHas_Right to i8*), i8* bitcast (i1 (i8*)* @Tree.GetHas_Left to i8*), i8* bitcast (i1 (i8*,i1)* @Tree.SetHas_Left to i8*), i8* bitcast (i1 (i8*,i1)* @Tree.SetHas_Right to i8*), i8* bitcast (i1 (i8*,i32,i32)* @Tree.Compare to i8*), i8* bitcast (i1 (i8*,i32)* @Tree.Insert to i8*), i8* bitcast (i1 (i8*,i32)* @Tree.Delete to i8*), i8* bitcast (i1 (i8*,i8*,i8*)* @Tree.Remove to i8*), i8* bitcast (i1 (i8*,i8*,i8*)* @Tree.RemoveRight to i8*), i8* bitcast (i1 (i8*,i8*,i8*)* @Tree.RemoveLeft to i8*), i8* bitcast (i32 (i8*,i32)* @Tree.Search to i8*), i8* bitcast (i1 (i8*)* @Tree.Print to i8*), i8* bitcast (i1 (i8*,i8*)* @Tree.RecPrint to i8*), i8* bitcast (i32 (i8*,i8*)* @Tree.accept to i8*)]
@.Visitor_vtable = global [1 x i8*] [i8* bitcast (i32 (i8*,i8*)* @Visitor.visit to i8*)]
@.MyVisitor_vtable = global [1 x i8*] [i8* bitcast (i32 (i8*,i8*)* @MyVisitor.visit to i8*)]

declare i8* @calloc(i32, i32)
declare i32 @printf(i8*, ...)
declare void @exit(i32)

@_cint = constant [4 x i8] c"%d\0a\00"
@_cOOB = constant [15 x i8] c"Out of bounds\0a\00"
define void @print_int(i32 %i) {
    %_str = bitcast [4 x i8]* @_cint to i8*
    call i32 (i8*, ...) @printf(i8* %_str, i32 %i)
    ret void
}

define void @throw_oob() {
    %_str = bitcast [15 x i8]* @_cOOB to i8*
    call i32 (i8*, ...) @printf(i8* %_str)
    call void @exit(i32 1)
    ret void
}
define i32 @main(){
	%_0 = call i8* @calloc(i32 1, i32 21)
	%_1 = bitcast i8* %_0 to i8***
	%_2 = getelementptr [1 x i8*], [1 x i8*]* @.TV_vtable, i32 0, i32 0
	store i8** %_2, i8*** %_1
	%_3 = bitcast i8* %_0 to i8***
	%_4 = load i8**,i8*** %_3
	%_5 = getelementptr i8*,i8** %_4, i32 0
	%_6 = load i8*, i8** %_5
	%_7 = bitcast i8* %_6 to i32 (i8*)*
	%_8 = call i32 %_7(i8* %_0)
	call void (i32) @print_int(i32 %_8)

	ret i32 0
}
define i32 @TV.Start(i8* %this)
{	%root = alloca i8*
	%ntb = alloca i1
	%nti = alloca i32
	%v = alloca i8*

	%_9 = call i8* @calloc(i32 1, i32 127)
	%_10 = bitcast i8* %_9 to i8***
	%_11 = getelementptr [21 x i8*], [21 x i8*]* @.Tree_vtable, i32 0, i32 0
	store i8** %_11, i8*** %_10
	store i8* %_9, i8** %root

	%_12 = load i8*, i8** %root

	%_13 = bitcast i8* %_12 to i8***
	%_14 = load i8**,i8*** %_13
	%_15 = getelementptr i8*,i8** %_14, i32 0
	%_16 = load i8*, i8** %_15
	%_17 = bitcast i8* %_16 to i1 (i8*,i32)*
	%_18 = call i1 %_17(i8* %_12, i32 16)
	store i1 %_18, i1* %ntb

	%_19 = load i8*, i8** %root

	%_20 = bitcast i8* %_19 to i8***
	%_21 = load i8**,i8*** %_20
	%_22 = getelementptr i8*,i8** %_21, i32 18
	%_23 = load i8*, i8** %_22
	%_24 = bitcast i8* %_23 to i1 (i8*)*
	%_25 = call i1 %_24(i8* %_19)
	store i1 %_25, i1* %ntb

	call void (i32) @print_int(i32 100000000)

	%_26 = load i8*, i8** %root

	%_27 = bitcast i8* %_26 to i8***
	%_28 = load i8**,i8*** %_27
	%_29 = getelementptr i8*,i8** %_28, i32 12
	%_30 = load i8*, i8** %_29
	%_31 = bitcast i8* %_30 to i1 (i8*,i32)*
	%_32 = call i1 %_31(i8* %_26, i32 8)
	store i1 %_32, i1* %ntb

	%_33 = load i8*, i8** %root

	%_34 = bitcast i8* %_33 to i8***
	%_35 = load i8**,i8*** %_34
	%_36 = getelementptr i8*,i8** %_35, i32 12
	%_37 = load i8*, i8** %_36
	%_38 = bitcast i8* %_37 to i1 (i8*,i32)*
	%_39 = call i1 %_38(i8* %_33, i32 24)
	store i1 %_39, i1* %ntb

	%_40 = load i8*, i8** %root

	%_41 = bitcast i8* %_40 to i8***
	%_42 = load i8**,i8*** %_41
	%_43 = getelementptr i8*,i8** %_42, i32 12
	%_44 = load i8*, i8** %_43
	%_45 = bitcast i8* %_44 to i1 (i8*,i32)*
	%_46 = call i1 %_45(i8* %_40, i32 4)
	store i1 %_46, i1* %ntb

	%_47 = load i8*, i8** %root

	%_48 = bitcast i8* %_47 to i8***
	%_49 = load i8**,i8*** %_48
	%_50 = getelementptr i8*,i8** %_49, i32 12
	%_51 = load i8*, i8** %_50
	%_52 = bitcast i8* %_51 to i1 (i8*,i32)*
	%_53 = call i1 %_52(i8* %_47, i32 12)
	store i1 %_53, i1* %ntb

	%_54 = load i8*, i8** %root

	%_55 = bitcast i8* %_54 to i8***
	%_56 = load i8**,i8*** %_55
	%_57 = getelementptr i8*,i8** %_56, i32 12
	%_58 = load i8*, i8** %_57
	%_59 = bitcast i8* %_58 to i1 (i8*,i32)*
	%_60 = call i1 %_59(i8* %_54, i32 20)
	store i1 %_60, i1* %ntb

	%_61 = load i8*, i8** %root

	%_62 = bitcast i8* %_61 to i8***
	%_63 = load i8**,i8*** %_62
	%_64 = getelementptr i8*,i8** %_63, i32 12
	%_65 = load i8*, i8** %_64
	%_66 = bitcast i8* %_65 to i1 (i8*,i32)*
	%_67 = call i1 %_66(i8* %_61, i32 28)
	store i1 %_67, i1* %ntb

	%_68 = load i8*, i8** %root

	%_69 = bitcast i8* %_68 to i8***
	%_70 = load i8**,i8*** %_69
	%_71 = getelementptr i8*,i8** %_70, i32 12
	%_72 = load i8*, i8** %_71
	%_73 = bitcast i8* %_72 to i1 (i8*,i32)*
	%_74 = call i1 %_73(i8* %_68, i32 14)
	store i1 %_74, i1* %ntb

	%_75 = load i8*, i8** %root

	%_76 = bitcast i8* %_75 to i8***
	%_77 = load i8**,i8*** %_76
	%_78 = getelementptr i8*,i8** %_77, i32 18
	%_79 = load i8*, i8** %_78
	%_80 = bitcast i8* %_79 to i1 (i8*)*
	%_81 = call i1 %_80(i8* %_75)
	store i1 %_81, i1* %ntb

	call void (i32) @print_int(i32 100000000)

	%_82 = call i8* @calloc(i32 1, i32 28)
	%_83 = bitcast i8* %_82 to i8***
	%_84 = getelementptr [1 x i8*], [1 x i8*]* @.MyVisitor_vtable, i32 0, i32 0
	store i8** %_84, i8*** %_83
	store i8* %_82, i8** %v

	call void (i32) @print_int(i32 50000000)

	%_85 = load i8*, i8** %root

	%_86 = bitcast i8* %_85 to i8***
	%_87 = load i8**,i8*** %_86
	%_88 = getelementptr i8*,i8** %_87, i32 20
	%_89 = load i8*, i8** %_88
	%_90 = bitcast i8* %_89 to i32 (i8*,i8*)*
	%_91 = load i8*, i8** %v

	%_92 = call i32 %_90(i8* %_85, i8* %_91)
	store i32 %_92, i32* %nti

	call void (i32) @print_int(i32 100000000)

	%_93 = load i8*, i8** %root

	%_94 = bitcast i8* %_93 to i8***
	%_95 = load i8**,i8*** %_94
	%_96 = getelementptr i8*,i8** %_95, i32 17
	%_97 = load i8*, i8** %_96
	%_98 = bitcast i8* %_97 to i32 (i8*,i32)*
	%_99 = call i32 %_98(i8* %_93, i32 24)
	call void (i32) @print_int(i32 %_99)

	%_100 = load i8*, i8** %root

	%_101 = bitcast i8* %_100 to i8***
	%_102 = load i8**,i8*** %_101
	%_103 = getelementptr i8*,i8** %_102, i32 17
	%_104 = load i8*, i8** %_103
	%_105 = bitcast i8* %_104 to i32 (i8*,i32)*
	%_106 = call i32 %_105(i8* %_100, i32 12)
	call void (i32) @print_int(i32 %_106)

	%_107 = load i8*, i8** %root

	%_108 = bitcast i8* %_107 to i8***
	%_109 = load i8**,i8*** %_108
	%_110 = getelementptr i8*,i8** %_109, i32 17
	%_111 = load i8*, i8** %_110
	%_112 = bitcast i8* %_111 to i32 (i8*,i32)*
	%_113 = call i32 %_112(i8* %_107, i32 16)
	call void (i32) @print_int(i32 %_113)

	%_114 = load i8*, i8** %root

	%_115 = bitcast i8* %_114 to i8***
	%_116 = load i8**,i8*** %_115
	%_117 = getelementptr i8*,i8** %_116, i32 17
	%_118 = load i8*, i8** %_117
	%_119 = bitcast i8* %_118 to i32 (i8*,i32)*
	%_120 = call i32 %_119(i8* %_114, i32 50)
	call void (i32) @print_int(i32 %_120)

	%_121 = load i8*, i8** %root

	%_122 = bitcast i8* %_121 to i8***
	%_123 = load i8**,i8*** %_122
	%_124 = getelementptr i8*,i8** %_123, i32 17
	%_125 = load i8*, i8** %_124
	%_126 = bitcast i8* %_125 to i32 (i8*,i32)*
	%_127 = call i32 %_126(i8* %_121, i32 12)
	call void (i32) @print_int(i32 %_127)

	%_128 = load i8*, i8** %root

	%_129 = bitcast i8* %_128 to i8***
	%_130 = load i8**,i8*** %_129
	%_131 = getelementptr i8*,i8** %_130, i32 13
	%_132 = load i8*, i8** %_131
	%_133 = bitcast i8* %_132 to i1 (i8*,i32)*
	%_134 = call i1 %_133(i8* %_128, i32 12)
	store i1 %_134, i1* %ntb

	%_135 = load i8*, i8** %root

	%_136 = bitcast i8* %_135 to i8***
	%_137 = load i8**,i8*** %_136
	%_138 = getelementptr i8*,i8** %_137, i32 18
	%_139 = load i8*, i8** %_138
	%_140 = bitcast i8* %_139 to i1 (i8*)*
	%_141 = call i1 %_140(i8* %_135)
	store i1 %_141, i1* %ntb

	%_142 = load i8*, i8** %root

	%_143 = bitcast i8* %_142 to i8***
	%_144 = load i8**,i8*** %_143
	%_145 = getelementptr i8*,i8** %_144, i32 17
	%_146 = load i8*, i8** %_145
	%_147 = bitcast i8* %_146 to i32 (i8*,i32)*
	%_148 = call i32 %_147(i8* %_142, i32 12)
	call void (i32) @print_int(i32 %_148)

	ret i32 0
}
define i1 @Tree.Init(i8* %this, i32 %.v_key)
{	%v_key = alloca i32
	store i32 %.v_key, i32* %v_key

	%_0 = load i32, i32* %v_key

	%_1 = getelementptr i8, i8* %this, i32 24
	%_2 = bitcast i8* %_1 to i32*
	store i32 %_0, i32* %_2

	%_3 = getelementptr i8, i8* %this, i32 28
	%_4 = bitcast i8* %_3 to i1*
	store i1 0, i1* %_4

	%_5 = getelementptr i8, i8* %this, i32 29
	%_6 = bitcast i8* %_5 to i1*
	store i1 0, i1* %_6

	ret i1 1
}
define i1 @Tree.SetRight(i8* %this, i8* %.rn)
{	%rn = alloca i8*
	store i8* %.rn, i8** %rn

	%_0 = load i8*, i8** %rn

	%_1 = getelementptr i8, i8* %this, i32 16
	%_2 = bitcast i8* %_1 to i8**
	store i8* %_0, i8** %_2

	ret i1 1
}
define i1 @Tree.SetLeft(i8* %this, i8* %.ln)
{	%ln = alloca i8*
	store i8* %.ln, i8** %ln

	%_0 = load i8*, i8** %ln

	%_1 = getelementptr i8, i8* %this, i32 8
	%_2 = bitcast i8* %_1 to i8**
	store i8* %_0, i8** %_2

	ret i1 1
}
define i8* @Tree.GetRight(i8* %this)
{
	%_0 = getelementptr i8, i8* %this, i32 16
	%_1 = bitcast i8* %_0 to i8**
	%_2 = load i8*, i8** %_1

	ret i8* %_2
}
define i8* @Tree.GetLeft(i8* %this)
{
	%_0 = getelementptr i8, i8* %this, i32 8
	%_1 = bitcast i8* %_0 to i8**
	%_2 = load i8*, i8** %_1

	ret i8* %_2
}
define i32 @Tree.GetKey(i8* %this)
{
	%_0 = getelementptr i8, i8* %this, i32 24
	%_1 = bitcast i8* %_0 to i32*
	%_2 = load i32, i32* %_1

	ret i32 %_2
}
define i1 @Tree.SetKey(i8* %this, i32 %.v_key)
{	%v_key = alloca i32
	store i32 %.v_key, i32* %v_key

	%_0 = load i32, i32* %v_key

	%_1 = getelementptr i8, i8* %this, i32 24
	%_2 = bitcast i8* %_1 to i32*
	store i32 %_0, i32* %_2

	ret i1 1
}
define i1 @Tree.GetHas_Right(i8* %this)
{
	%_0 = getelementptr i8, i8* %this, i32 29
	%_1 = bitcast i8* %_0 to i1*
	%_2 = load i1, i1* %_1

	ret i1 %_2
}
define i1 @Tree.GetHas_Left(i8* %this)
{
	%_0 = getelementptr i8, i8* %this, i32 28
	%_1 = bitcast i8* %_0 to i1*
	%_2 = load i1, i1* %_1

	ret i1 %_2
}
define i1 @Tree.SetHas_Left(i8* %this, i1 %.val)
{	%val = alloca i1
	store i1 %.val, i1* %val

	%_0 = load i1, i1* %val

	%_1 = getelementptr i8, i8* %this, i32 28
	%_2 = bitcast i8* %_1 to i1*
	store i1 %_0, i1* %_2

	ret i1 1
}
define i1 @Tree.SetHas_Right(i8* %this, i1 %.val)
{	%val = alloca i1
	store i1 %.val, i1* %val

	%_0 = load i1, i1* %val

	%_1 = getelementptr i8, i8* %this, i32 29
	%_2 = bitcast i8* %_1 to i1*
	store i1 %_0, i1* %_2

	ret i1 1
}
define i1 @Tree.Compare(i8* %this, i32 %.num1, i32 %.num2)
{	%num1 = alloca i32
	store i32 %.num1, i32* %num1
	%num2 = alloca i32
	store i32 %.num2, i32* %num2
	%ntb = alloca i1
	%nti = alloca i32

	store i1 0, i1* %ntb

	%_0 = load i32, i32* %num2

	%_1 = add i32 %_0, 1
	store i32 %_1, i32* %nti

	%_2 = load i32, i32* %num1

	%_3 = load i32, i32* %num2

	%_4 =icmp slt i32 %_2, %_3
    br  i1 %_4, label %if0, label %if1

if0:
	store i1 0, i1* %ntb

    br label %if2

if1:

	%_5 = load i32, i32* %num1

	%_6 = load i32, i32* %nti

	%_7 =icmp slt i32 %_5, %_6	%_8= xor i1 1, %_7
    br  i1 %_8, label %if3, label %if4

if3:
	store i1 0, i1* %ntb

    br label %if5

if4:

	store i1 1, i1* %ntb

    br label %if5

if5:

    br label %if2

if2:

	%_9 = load i1, i1* %ntb

	ret i1 %_9
}
define i1 @Tree.Insert(i8* %this, i32 %.v_key)
{	%v_key = alloca i32
	store i32 %.v_key, i32* %v_key
	%new_node = alloca i8*
	%ntb = alloca i1
	%current_node = alloca i8*
	%cont = alloca i1
	%key_aux = alloca i32

	%_0 = call i8* @calloc(i32 1, i32 127)
	%_1 = bitcast i8* %_0 to i8***
	%_2 = getelementptr [21 x i8*], [21 x i8*]* @.Tree_vtable, i32 0, i32 0
	store i8** %_2, i8*** %_1
	store i8* %_0, i8** %new_node

	%_3 = load i8*, i8** %new_node

	%_4 = bitcast i8* %_3 to i8***
	%_5 = load i8**,i8*** %_4
	%_6 = getelementptr i8*,i8** %_5, i32 0
	%_7 = load i8*, i8** %_6
	%_8 = bitcast i8* %_7 to i1 (i8*,i32)*
	%_9 = load i32, i32* %v_key

	%_10 = call i1 %_8(i8* %_3, i32 %_9)
	store i1 %_10, i1* %ntb

	store i8* %this, i8** %current_node

	store i1 1, i1* %cont

	br label %loop1

loop1:
	%_11 = load i1, i1* %cont

	 br i1 %_11, label %loop2, label %loop3

loop2:
	%_12 = load i8*, i8** %current_node

	%_13 = bitcast i8* %_12 to i8***
	%_14 = load i8**,i8*** %_13
	%_15 = getelementptr i8*,i8** %_14, i32 5
	%_16 = load i8*, i8** %_15
	%_17 = bitcast i8* %_16 to i32 (i8*)*
	%_18 = call i32 %_17(i8* %_12)
	store i32 %_18, i32* %key_aux

	%_19 = load i32, i32* %v_key

	%_20 = load i32, i32* %key_aux

	%_21 =icmp slt i32 %_19, %_20
    br  i1 %_21, label %if0, label %if1

if0:
	%_22 = load i8*, i8** %current_node

	%_23 = bitcast i8* %_22 to i8***
	%_24 = load i8**,i8*** %_23
	%_25 = getelementptr i8*,i8** %_24, i32 8
	%_26 = load i8*, i8** %_25
	%_27 = bitcast i8* %_26 to i1 (i8*)*
	%_28 = call i1 %_27(i8* %_22)
    br  i1 %_28, label %if2, label %if3

if2:
	%_29 = load i8*, i8** %current_node

	%_30 = bitcast i8* %_29 to i8***
	%_31 = load i8**,i8*** %_30
	%_32 = getelementptr i8*,i8** %_31, i32 4
	%_33 = load i8*, i8** %_32
	%_34 = bitcast i8* %_33 to i8* (i8*)*
	%_35 = call i8* %_34(i8* %_29)
	store i8* %_35, i8** %current_node

    br label %if4

if3:

	store i1 0, i1* %cont

	%_36 = load i8*, i8** %current_node

	%_37 = bitcast i8* %_36 to i8***
	%_38 = load i8**,i8*** %_37
	%_39 = getelementptr i8*,i8** %_38, i32 9
	%_40 = load i8*, i8** %_39
	%_41 = bitcast i8* %_40 to i1 (i8*,i1)*
	%_42 = call i1 %_41(i8* %_36, i1 1)
	store i1 %_42, i1* %ntb

	%_43 = load i8*, i8** %current_node

	%_44 = bitcast i8* %_43 to i8***
	%_45 = load i8**,i8*** %_44
	%_46 = getelementptr i8*,i8** %_45, i32 2
	%_47 = load i8*, i8** %_46
	%_48 = bitcast i8* %_47 to i1 (i8*,i8*)*
	%_49 = load i8*, i8** %new_node

	%_50 = call i1 %_48(i8* %_43, i8* %_49)
	store i1 %_50, i1* %ntb

    br label %if4

if4:

    br label %if5

if1:

	%_51 = load i8*, i8** %current_node

	%_52 = bitcast i8* %_51 to i8***
	%_53 = load i8**,i8*** %_52
	%_54 = getelementptr i8*,i8** %_53, i32 7
	%_55 = load i8*, i8** %_54
	%_56 = bitcast i8* %_55 to i1 (i8*)*
	%_57 = call i1 %_56(i8* %_51)
    br  i1 %_57, label %if6, label %if7

if6:
	%_58 = load i8*, i8** %current_node

	%_59 = bitcast i8* %_58 to i8***
	%_60 = load i8**,i8*** %_59
	%_61 = getelementptr i8*,i8** %_60, i32 3
	%_62 = load i8*, i8** %_61
	%_63 = bitcast i8* %_62 to i8* (i8*)*
	%_64 = call i8* %_63(i8* %_58)
	store i8* %_64, i8** %current_node

    br label %if8

if7:

	store i1 0, i1* %cont

	%_65 = load i8*, i8** %current_node

	%_66 = bitcast i8* %_65 to i8***
	%_67 = load i8**,i8*** %_66
	%_68 = getelementptr i8*,i8** %_67, i32 10
	%_69 = load i8*, i8** %_68
	%_70 = bitcast i8* %_69 to i1 (i8*,i1)*
	%_71 = call i1 %_70(i8* %_65, i1 1)
	store i1 %_71, i1* %ntb

	%_72 = load i8*, i8** %current_node

	%_73 = bitcast i8* %_72 to i8***
	%_74 = load i8**,i8*** %_73
	%_75 = getelementptr i8*,i8** %_74, i32 1
	%_76 = load i8*, i8** %_75
	%_77 = bitcast i8* %_76 to i1 (i8*,i8*)*
	%_78 = load i8*, i8** %new_node

	%_79 = call i1 %_77(i8* %_72, i8* %_78)
	store i1 %_79, i1* %ntb

    br label %if8

if8:

    br label %if5

if5:

	br label %loop1

loop3:
	ret i1 1
}
define i1 @Tree.Delete(i8* %this, i32 %.v_key)
{	%v_key = alloca i32
	store i32 %.v_key, i32* %v_key
	%current_node = alloca i8*
	%parent_node = alloca i8*
	%cont = alloca i1
	%found = alloca i1
	%ntb = alloca i1
	%is_root = alloca i1
	%key_aux = alloca i32

	store i8* %this, i8** %current_node

	store i8* %this, i8** %parent_node

	store i1 1, i1* %cont

	store i1 0, i1* %found

	store i1 1, i1* %is_root

	br label %loop1

loop1:
	%_0 = load i1, i1* %cont

	 br i1 %_0, label %loop2, label %loop3

loop2:
	%_1 = load i8*, i8** %current_node

	%_2 = bitcast i8* %_1 to i8***
	%_3 = load i8**,i8*** %_2
	%_4 = getelementptr i8*,i8** %_3, i32 5
	%_5 = load i8*, i8** %_4
	%_6 = bitcast i8* %_5 to i32 (i8*)*
	%_7 = call i32 %_6(i8* %_1)
	store i32 %_7, i32* %key_aux

	%_8 = load i32, i32* %v_key

	%_9 = load i32, i32* %key_aux

	%_10 =icmp slt i32 %_8, %_9
    br  i1 %_10, label %if0, label %if1

if0:
	%_11 = load i8*, i8** %current_node

	%_12 = bitcast i8* %_11 to i8***
	%_13 = load i8**,i8*** %_12
	%_14 = getelementptr i8*,i8** %_13, i32 8
	%_15 = load i8*, i8** %_14
	%_16 = bitcast i8* %_15 to i1 (i8*)*
	%_17 = call i1 %_16(i8* %_11)
    br  i1 %_17, label %if2, label %if3

if2:
	%_18 = load i8*, i8** %current_node

	store i8* %_18, i8** %parent_node

	%_19 = load i8*, i8** %current_node

	%_20 = bitcast i8* %_19 to i8***
	%_21 = load i8**,i8*** %_20
	%_22 = getelementptr i8*,i8** %_21, i32 4
	%_23 = load i8*, i8** %_22
	%_24 = bitcast i8* %_23 to i8* (i8*)*
	%_25 = call i8* %_24(i8* %_19)
	store i8* %_25, i8** %current_node

    br label %if4

if3:

	store i1 0, i1* %cont

    br label %if4

if4:

    br label %if5

if1:

	%_26 = load i32, i32* %key_aux

	%_27 = load i32, i32* %v_key

	%_28 =icmp slt i32 %_26, %_27
    br  i1 %_28, label %if6, label %if7

if6:
	%_29 = load i8*, i8** %current_node

	%_30 = bitcast i8* %_29 to i8***
	%_31 = load i8**,i8*** %_30
	%_32 = getelementptr i8*,i8** %_31, i32 7
	%_33 = load i8*, i8** %_32
	%_34 = bitcast i8* %_33 to i1 (i8*)*
	%_35 = call i1 %_34(i8* %_29)
    br  i1 %_35, label %if8, label %if9

if8:
	%_36 = load i8*, i8** %current_node

	store i8* %_36, i8** %parent_node

	%_37 = load i8*, i8** %current_node

	%_38 = bitcast i8* %_37 to i8***
	%_39 = load i8**,i8*** %_38
	%_40 = getelementptr i8*,i8** %_39, i32 3
	%_41 = load i8*, i8** %_40
	%_42 = bitcast i8* %_41 to i8* (i8*)*
	%_43 = call i8* %_42(i8* %_37)
	store i8* %_43, i8** %current_node

    br label %if10

if9:

	store i1 0, i1* %cont

    br label %if10

if10:

    br label %if11

if7:

	%_44 = load i1, i1* %is_root

    br  i1 %_44, label %if12, label %if13

if12:
	%_45 = load i8*, i8** %current_node

	%_46 = bitcast i8* %_45 to i8***
	%_47 = load i8**,i8*** %_46
	%_48 = getelementptr i8*,i8** %_47, i32 7
	%_49 = load i8*, i8** %_48
	%_50 = bitcast i8* %_49 to i1 (i8*)*
	%_51 = call i1 %_50(i8* %_45)	%_52= xor i1 1, %_51
	br label %andexpre1

andexpre1:
	br i1 %_52, label %andexpre2, label %andexpre4

andexpre2:
	%_53 = load i8*, i8** %current_node

	%_54 = bitcast i8* %_53 to i8***
	%_55 = load i8**,i8*** %_54
	%_56 = getelementptr i8*,i8** %_55, i32 8
	%_57 = load i8*, i8** %_56
	%_58 = bitcast i8* %_57 to i1 (i8*)*
	%_59 = call i1 %_58(i8* %_53)	%_60= xor i1 1, %_59
	br label %andexpre3

andexpre3:
	br label %andexpre4

andexpre4:
	%_61 = phi i1 [0, %andexpre1], [ %_60, %andexpre3 ]
    br  i1%_61, label %if14, label %if15

if14:
	store i1 1, i1* %ntb

    br label %if16

if15:

	%_62 = bitcast i8* %this to i8***
	%_63 = load i8**,i8*** %_62
	%_64 = getelementptr i8*,i8** %_63, i32 14
	%_65 = load i8*, i8** %_64
	%_66 = bitcast i8* %_65 to i1 (i8*,i8*,i8*)*
	%_67 = load i8*, i8** %parent_node

	%_68 = load i8*, i8** %current_node

	%_69 = call i1 %_66(i8* %this, i8* %_67, i8* %_68)
	store i1 %_69, i1* %ntb

    br label %if16

if16:

    br label %if17

if13:

	%_70 = bitcast i8* %this to i8***
	%_71 = load i8**,i8*** %_70
	%_72 = getelementptr i8*,i8** %_71, i32 14
	%_73 = load i8*, i8** %_72
	%_74 = bitcast i8* %_73 to i1 (i8*,i8*,i8*)*
	%_75 = load i8*, i8** %parent_node

	%_76 = load i8*, i8** %current_node

	%_77 = call i1 %_74(i8* %this, i8* %_75, i8* %_76)
	store i1 %_77, i1* %ntb

    br label %if17

if17:

	store i1 1, i1* %found

	store i1 0, i1* %cont

    br label %if11

if11:

    br label %if5

if5:

	store i1 0, i1* %is_root

	br label %loop1

loop3:
	%_78 = load i1, i1* %found

	ret i1 %_78
}
define i1 @Tree.Remove(i8* %this, i8* %.p_node, i8* %.c_node)
{	%p_node = alloca i8*
	store i8* %.p_node, i8** %p_node
	%c_node = alloca i8*
	store i8* %.c_node, i8** %c_node
	%ntb = alloca i1
	%auxkey1 = alloca i32
	%auxkey2 = alloca i32

	%_0 = load i8*, i8** %c_node

	%_1 = bitcast i8* %_0 to i8***
	%_2 = load i8**,i8*** %_1
	%_3 = getelementptr i8*,i8** %_2, i32 8
	%_4 = load i8*, i8** %_3
	%_5 = bitcast i8* %_4 to i1 (i8*)*
	%_6 = call i1 %_5(i8* %_0)
    br  i1 %_6, label %if0, label %if1

if0:
	%_7 = bitcast i8* %this to i8***
	%_8 = load i8**,i8*** %_7
	%_9 = getelementptr i8*,i8** %_8, i32 16
	%_10 = load i8*, i8** %_9
	%_11 = bitcast i8* %_10 to i1 (i8*,i8*,i8*)*
	%_12 = load i8*, i8** %p_node

	%_13 = load i8*, i8** %c_node

	%_14 = call i1 %_11(i8* %this, i8* %_12, i8* %_13)
	store i1 %_14, i1* %ntb

    br label %if2

if1:

	%_15 = load i8*, i8** %c_node

	%_16 = bitcast i8* %_15 to i8***
	%_17 = load i8**,i8*** %_16
	%_18 = getelementptr i8*,i8** %_17, i32 7
	%_19 = load i8*, i8** %_18
	%_20 = bitcast i8* %_19 to i1 (i8*)*
	%_21 = call i1 %_20(i8* %_15)
    br  i1 %_21, label %if3, label %if4

if3:
	%_22 = bitcast i8* %this to i8***
	%_23 = load i8**,i8*** %_22
	%_24 = getelementptr i8*,i8** %_23, i32 15
	%_25 = load i8*, i8** %_24
	%_26 = bitcast i8* %_25 to i1 (i8*,i8*,i8*)*
	%_27 = load i8*, i8** %p_node

	%_28 = load i8*, i8** %c_node

	%_29 = call i1 %_26(i8* %this, i8* %_27, i8* %_28)
	store i1 %_29, i1* %ntb

    br label %if5

if4:

	%_30 = load i8*, i8** %c_node

	%_31 = bitcast i8* %_30 to i8***
	%_32 = load i8**,i8*** %_31
	%_33 = getelementptr i8*,i8** %_32, i32 5
	%_34 = load i8*, i8** %_33
	%_35 = bitcast i8* %_34 to i32 (i8*)*
	%_36 = call i32 %_35(i8* %_30)
	store i32 %_36, i32* %auxkey1

	%_37 = load i8*, i8** %p_node

	%_38 = bitcast i8* %_37 to i8***
	%_39 = load i8**,i8*** %_38
	%_40 = getelementptr i8*,i8** %_39, i32 4
	%_41 = load i8*, i8** %_40
	%_42 = bitcast i8* %_41 to i8* (i8*)*
	%_43 = call i8* %_42(i8* %_37)
	%_44 = bitcast i8* %_43 to i8***
	%_45 = load i8**,i8*** %_44
	%_46 = getelementptr i8*,i8** %_45, i32 5
	%_47 = load i8*, i8** %_46
	%_48 = bitcast i8* %_47 to i32 (i8*)*
	%_49 = call i32 %_48(i8* %_43)
	store i32 %_49, i32* %auxkey2

	%_50 = bitcast i8* %this to i8***
	%_51 = load i8**,i8*** %_50
	%_52 = getelementptr i8*,i8** %_51, i32 11
	%_53 = load i8*, i8** %_52
	%_54 = bitcast i8* %_53 to i1 (i8*,i32,i32)*
	%_55 = load i32, i32* %auxkey1

	%_56 = load i32, i32* %auxkey2

	%_57 = call i1 %_54(i8* %this, i32 %_55, i32 %_56)
    br  i1 %_57, label %if6, label %if7

if6:
	%_58 = load i8*, i8** %p_node

	%_59 = bitcast i8* %_58 to i8***
	%_60 = load i8**,i8*** %_59
	%_61 = getelementptr i8*,i8** %_60, i32 2
	%_62 = load i8*, i8** %_61
	%_63 = bitcast i8* %_62 to i1 (i8*,i8*)*
	%_64 = getelementptr i8, i8* %this, i32 30
	%_65 = bitcast i8* %_64 to i8**
	%_66 = load i8*, i8** %_65

	%_67 = call i1 %_63(i8* %_58, i8* %_66)
	store i1 %_67, i1* %ntb

	%_68 = load i8*, i8** %p_node

	%_69 = bitcast i8* %_68 to i8***
	%_70 = load i8**,i8*** %_69
	%_71 = getelementptr i8*,i8** %_70, i32 9
	%_72 = load i8*, i8** %_71
	%_73 = bitcast i8* %_72 to i1 (i8*,i1)*
	%_74 = call i1 %_73(i8* %_68, i1 0)
	store i1 %_74, i1* %ntb

    br label %if8

if7:

	%_75 = load i8*, i8** %p_node

	%_76 = bitcast i8* %_75 to i8***
	%_77 = load i8**,i8*** %_76
	%_78 = getelementptr i8*,i8** %_77, i32 1
	%_79 = load i8*, i8** %_78
	%_80 = bitcast i8* %_79 to i1 (i8*,i8*)*
	%_81 = getelementptr i8, i8* %this, i32 30
	%_82 = bitcast i8* %_81 to i8**
	%_83 = load i8*, i8** %_82

	%_84 = call i1 %_80(i8* %_75, i8* %_83)
	store i1 %_84, i1* %ntb

	%_85 = load i8*, i8** %p_node

	%_86 = bitcast i8* %_85 to i8***
	%_87 = load i8**,i8*** %_86
	%_88 = getelementptr i8*,i8** %_87, i32 10
	%_89 = load i8*, i8** %_88
	%_90 = bitcast i8* %_89 to i1 (i8*,i1)*
	%_91 = call i1 %_90(i8* %_85, i1 0)
	store i1 %_91, i1* %ntb

    br label %if8

if8:

    br label %if5

if5:

    br label %if2

if2:

	ret i1 1
}
define i1 @Tree.RemoveRight(i8* %this, i8* %.p_node, i8* %.c_node)
{	%p_node = alloca i8*
	store i8* %.p_node, i8** %p_node
	%c_node = alloca i8*
	store i8* %.c_node, i8** %c_node
	%ntb = alloca i1

	br label %loop1

loop1:
	%_0 = load i8*, i8** %c_node

	%_1 = bitcast i8* %_0 to i8***
	%_2 = load i8**,i8*** %_1
	%_3 = getelementptr i8*,i8** %_2, i32 7
	%_4 = load i8*, i8** %_3
	%_5 = bitcast i8* %_4 to i1 (i8*)*
	%_6 = call i1 %_5(i8* %_0)
	 br i1 %_6, label %loop2, label %loop3

loop2:
	%_7 = load i8*, i8** %c_node

	%_8 = bitcast i8* %_7 to i8***
	%_9 = load i8**,i8*** %_8
	%_10 = getelementptr i8*,i8** %_9, i32 6
	%_11 = load i8*, i8** %_10
	%_12 = bitcast i8* %_11 to i1 (i8*,i32)*
	%_13 = load i8*, i8** %c_node

	%_14 = bitcast i8* %_13 to i8***
	%_15 = load i8**,i8*** %_14
	%_16 = getelementptr i8*,i8** %_15, i32 3
	%_17 = load i8*, i8** %_16
	%_18 = bitcast i8* %_17 to i8* (i8*)*
	%_19 = call i8* %_18(i8* %_13)
	%_20 = bitcast i8* %_19 to i8***
	%_21 = load i8**,i8*** %_20
	%_22 = getelementptr i8*,i8** %_21, i32 5
	%_23 = load i8*, i8** %_22
	%_24 = bitcast i8* %_23 to i32 (i8*)*
	%_25 = call i32 %_24(i8* %_19)
	%_26 = call i1 %_12(i8* %_7, i32 %_25)
	store i1 %_26, i1* %ntb

	%_27 = load i8*, i8** %c_node

	store i8* %_27, i8** %p_node

	%_28 = load i8*, i8** %c_node

	%_29 = bitcast i8* %_28 to i8***
	%_30 = load i8**,i8*** %_29
	%_31 = getelementptr i8*,i8** %_30, i32 3
	%_32 = load i8*, i8** %_31
	%_33 = bitcast i8* %_32 to i8* (i8*)*
	%_34 = call i8* %_33(i8* %_28)
	store i8* %_34, i8** %c_node

	br label %loop1

loop3:
	%_35 = load i8*, i8** %p_node

	%_36 = bitcast i8* %_35 to i8***
	%_37 = load i8**,i8*** %_36
	%_38 = getelementptr i8*,i8** %_37, i32 1
	%_39 = load i8*, i8** %_38
	%_40 = bitcast i8* %_39 to i1 (i8*,i8*)*
	%_41 = getelementptr i8, i8* %this, i32 30
	%_42 = bitcast i8* %_41 to i8**
	%_43 = load i8*, i8** %_42

	%_44 = call i1 %_40(i8* %_35, i8* %_43)
	store i1 %_44, i1* %ntb

	%_45 = load i8*, i8** %p_node

	%_46 = bitcast i8* %_45 to i8***
	%_47 = load i8**,i8*** %_46
	%_48 = getelementptr i8*,i8** %_47, i32 10
	%_49 = load i8*, i8** %_48
	%_50 = bitcast i8* %_49 to i1 (i8*,i1)*
	%_51 = call i1 %_50(i8* %_45, i1 0)
	store i1 %_51, i1* %ntb

	ret i1 1
}
define i1 @Tree.RemoveLeft(i8* %this, i8* %.p_node, i8* %.c_node)
{	%p_node = alloca i8*
	store i8* %.p_node, i8** %p_node
	%c_node = alloca i8*
	store i8* %.c_node, i8** %c_node
	%ntb = alloca i1

	br label %loop1

loop1:
	%_0 = load i8*, i8** %c_node

	%_1 = bitcast i8* %_0 to i8***
	%_2 = load i8**,i8*** %_1
	%_3 = getelementptr i8*,i8** %_2, i32 8
	%_4 = load i8*, i8** %_3
	%_5 = bitcast i8* %_4 to i1 (i8*)*
	%_6 = call i1 %_5(i8* %_0)
	 br i1 %_6, label %loop2, label %loop3

loop2:
	%_7 = load i8*, i8** %c_node

	%_8 = bitcast i8* %_7 to i8***
	%_9 = load i8**,i8*** %_8
	%_10 = getelementptr i8*,i8** %_9, i32 6
	%_11 = load i8*, i8** %_10
	%_12 = bitcast i8* %_11 to i1 (i8*,i32)*
	%_13 = load i8*, i8** %c_node

	%_14 = bitcast i8* %_13 to i8***
	%_15 = load i8**,i8*** %_14
	%_16 = getelementptr i8*,i8** %_15, i32 4
	%_17 = load i8*, i8** %_16
	%_18 = bitcast i8* %_17 to i8* (i8*)*
	%_19 = call i8* %_18(i8* %_13)
	%_20 = bitcast i8* %_19 to i8***
	%_21 = load i8**,i8*** %_20
	%_22 = getelementptr i8*,i8** %_21, i32 5
	%_23 = load i8*, i8** %_22
	%_24 = bitcast i8* %_23 to i32 (i8*)*
	%_25 = call i32 %_24(i8* %_19)
	%_26 = call i1 %_12(i8* %_7, i32 %_25)
	store i1 %_26, i1* %ntb

	%_27 = load i8*, i8** %c_node

	store i8* %_27, i8** %p_node

	%_28 = load i8*, i8** %c_node

	%_29 = bitcast i8* %_28 to i8***
	%_30 = load i8**,i8*** %_29
	%_31 = getelementptr i8*,i8** %_30, i32 4
	%_32 = load i8*, i8** %_31
	%_33 = bitcast i8* %_32 to i8* (i8*)*
	%_34 = call i8* %_33(i8* %_28)
	store i8* %_34, i8** %c_node

	br label %loop1

loop3:
	%_35 = load i8*, i8** %p_node

	%_36 = bitcast i8* %_35 to i8***
	%_37 = load i8**,i8*** %_36
	%_38 = getelementptr i8*,i8** %_37, i32 2
	%_39 = load i8*, i8** %_38
	%_40 = bitcast i8* %_39 to i1 (i8*,i8*)*
	%_41 = getelementptr i8, i8* %this, i32 30
	%_42 = bitcast i8* %_41 to i8**
	%_43 = load i8*, i8** %_42

	%_44 = call i1 %_40(i8* %_35, i8* %_43)
	store i1 %_44, i1* %ntb

	%_45 = load i8*, i8** %p_node

	%_46 = bitcast i8* %_45 to i8***
	%_47 = load i8**,i8*** %_46
	%_48 = getelementptr i8*,i8** %_47, i32 9
	%_49 = load i8*, i8** %_48
	%_50 = bitcast i8* %_49 to i1 (i8*,i1)*
	%_51 = call i1 %_50(i8* %_45, i1 0)
	store i1 %_51, i1* %ntb

	ret i1 1
}
define i32 @Tree.Search(i8* %this, i32 %.v_key)
{	%v_key = alloca i32
	store i32 %.v_key, i32* %v_key
	%current_node = alloca i8*
	%ifound = alloca i32
	%cont = alloca i1
	%key_aux = alloca i32

	store i8* %this, i8** %current_node

	store i1 1, i1* %cont

	store i32 0, i32* %ifound

	br label %loop1

loop1:
	%_0 = load i1, i1* %cont

	 br i1 %_0, label %loop2, label %loop3

loop2:
	%_1 = load i8*, i8** %current_node

	%_2 = bitcast i8* %_1 to i8***
	%_3 = load i8**,i8*** %_2
	%_4 = getelementptr i8*,i8** %_3, i32 5
	%_5 = load i8*, i8** %_4
	%_6 = bitcast i8* %_5 to i32 (i8*)*
	%_7 = call i32 %_6(i8* %_1)
	store i32 %_7, i32* %key_aux

	%_8 = load i32, i32* %v_key

	%_9 = load i32, i32* %key_aux

	%_10 =icmp slt i32 %_8, %_9
    br  i1 %_10, label %if0, label %if1

if0:
	%_11 = load i8*, i8** %current_node

	%_12 = bitcast i8* %_11 to i8***
	%_13 = load i8**,i8*** %_12
	%_14 = getelementptr i8*,i8** %_13, i32 8
	%_15 = load i8*, i8** %_14
	%_16 = bitcast i8* %_15 to i1 (i8*)*
	%_17 = call i1 %_16(i8* %_11)
    br  i1 %_17, label %if2, label %if3

if2:
	%_18 = load i8*, i8** %current_node

	%_19 = bitcast i8* %_18 to i8***
	%_20 = load i8**,i8*** %_19
	%_21 = getelementptr i8*,i8** %_20, i32 4
	%_22 = load i8*, i8** %_21
	%_23 = bitcast i8* %_22 to i8* (i8*)*
	%_24 = call i8* %_23(i8* %_18)
	store i8* %_24, i8** %current_node

    br label %if4

if3:

	store i1 0, i1* %cont

    br label %if4

if4:

    br label %if5

if1:

	%_25 = load i32, i32* %key_aux

	%_26 = load i32, i32* %v_key

	%_27 =icmp slt i32 %_25, %_26
    br  i1 %_27, label %if6, label %if7

if6:
	%_28 = load i8*, i8** %current_node

	%_29 = bitcast i8* %_28 to i8***
	%_30 = load i8**,i8*** %_29
	%_31 = getelementptr i8*,i8** %_30, i32 7
	%_32 = load i8*, i8** %_31
	%_33 = bitcast i8* %_32 to i1 (i8*)*
	%_34 = call i1 %_33(i8* %_28)
    br  i1 %_34, label %if8, label %if9

if8:
	%_35 = load i8*, i8** %current_node

	%_36 = bitcast i8* %_35 to i8***
	%_37 = load i8**,i8*** %_36
	%_38 = getelementptr i8*,i8** %_37, i32 3
	%_39 = load i8*, i8** %_38
	%_40 = bitcast i8* %_39 to i8* (i8*)*
	%_41 = call i8* %_40(i8* %_35)
	store i8* %_41, i8** %current_node

    br label %if10

if9:

	store i1 0, i1* %cont

    br label %if10

if10:

    br label %if11

if7:

	store i32 1, i32* %ifound

	store i1 0, i1* %cont

    br label %if11

if11:

    br label %if5

if5:

	br label %loop1

loop3:
	%_42 = load i32, i32* %ifound

	ret i32 %_42
}
define i1 @Tree.Print(i8* %this)
{	%ntb = alloca i1
	%current_node = alloca i8*

	store i8* %this, i8** %current_node

	%_0 = bitcast i8* %this to i8***
	%_1 = load i8**,i8*** %_0
	%_2 = getelementptr i8*,i8** %_1, i32 19
	%_3 = load i8*, i8** %_2
	%_4 = bitcast i8* %_3 to i1 (i8*,i8*)*
	%_5 = load i8*, i8** %current_node

	%_6 = call i1 %_4(i8* %this, i8* %_5)
	store i1 %_6, i1* %ntb

	ret i1 1
}
define i1 @Tree.RecPrint(i8* %this, i8* %.node)
{	%node = alloca i8*
	store i8* %.node, i8** %node
	%ntb = alloca i1

	%_0 = load i8*, i8** %node

	%_1 = bitcast i8* %_0 to i8***
	%_2 = load i8**,i8*** %_1
	%_3 = getelementptr i8*,i8** %_2, i32 8
	%_4 = load i8*, i8** %_3
	%_5 = bitcast i8* %_4 to i1 (i8*)*
	%_6 = call i1 %_5(i8* %_0)
    br  i1 %_6, label %if0, label %if1

if0:
	%_7 = bitcast i8* %this to i8***
	%_8 = load i8**,i8*** %_7
	%_9 = getelementptr i8*,i8** %_8, i32 19
	%_10 = load i8*, i8** %_9
	%_11 = bitcast i8* %_10 to i1 (i8*,i8*)*
	%_12 = load i8*, i8** %node

	%_13 = bitcast i8* %_12 to i8***
	%_14 = load i8**,i8*** %_13
	%_15 = getelementptr i8*,i8** %_14, i32 4
	%_16 = load i8*, i8** %_15
	%_17 = bitcast i8* %_16 to i8* (i8*)*
	%_18 = call i8* %_17(i8* %_12)
	%_19 = call i1 %_11(i8* %this, i8* %_18)
	store i1 %_19, i1* %ntb

    br label %if2

if1:

	store i1 1, i1* %ntb

    br label %if2

if2:

	%_20 = load i8*, i8** %node

	%_21 = bitcast i8* %_20 to i8***
	%_22 = load i8**,i8*** %_21
	%_23 = getelementptr i8*,i8** %_22, i32 5
	%_24 = load i8*, i8** %_23
	%_25 = bitcast i8* %_24 to i32 (i8*)*
	%_26 = call i32 %_25(i8* %_20)
	call void (i32) @print_int(i32 %_26)

	%_27 = load i8*, i8** %node

	%_28 = bitcast i8* %_27 to i8***
	%_29 = load i8**,i8*** %_28
	%_30 = getelementptr i8*,i8** %_29, i32 7
	%_31 = load i8*, i8** %_30
	%_32 = bitcast i8* %_31 to i1 (i8*)*
	%_33 = call i1 %_32(i8* %_27)
    br  i1 %_33, label %if3, label %if4

if3:
	%_34 = bitcast i8* %this to i8***
	%_35 = load i8**,i8*** %_34
	%_36 = getelementptr i8*,i8** %_35, i32 19
	%_37 = load i8*, i8** %_36
	%_38 = bitcast i8* %_37 to i1 (i8*,i8*)*
	%_39 = load i8*, i8** %node

	%_40 = bitcast i8* %_39 to i8***
	%_41 = load i8**,i8*** %_40
	%_42 = getelementptr i8*,i8** %_41, i32 3
	%_43 = load i8*, i8** %_42
	%_44 = bitcast i8* %_43 to i8* (i8*)*
	%_45 = call i8* %_44(i8* %_39)
	%_46 = call i1 %_38(i8* %this, i8* %_45)
	store i1 %_46, i1* %ntb

    br label %if5

if4:

	store i1 1, i1* %ntb

    br label %if5

if5:

	ret i1 1
}
define i32 @Tree.accept(i8* %this, i8* %.v)
{	%v = alloca i8*
	store i8* %.v, i8** %v
	%nti = alloca i32

	call void (i32) @print_int(i32 333)

	%_0 = load i8*, i8** %v

	%_1 = bitcast i8* %_0 to i8***
	%_2 = load i8**,i8*** %_1
	%_3 = getelementptr i8*,i8** %_2, i32 0
	%_4 = load i8*, i8** %_3
	%_5 = bitcast i8* %_4 to i32 (i8*,i8*)*
	%_6 = call i32 %_5(i8* %_0, i8* %this)
	store i32 %_6, i32* %nti

	ret i32 0
}
define i32 @Visitor.visit(i8* %this, i8* %.n)
{	%n = alloca i8*
	store i8* %.n, i8** %n
	%nti = alloca i32

	%_0 = load i8*, i8** %n

	%_1 = bitcast i8* %_0 to i8***
	%_2 = load i8**,i8*** %_1
	%_3 = getelementptr i8*,i8** %_2, i32 7
	%_4 = load i8*, i8** %_3
	%_5 = bitcast i8* %_4 to i1 (i8*)*
	%_6 = call i1 %_5(i8* %_0)
    br  i1 %_6, label %if0, label %if1

if0:
	%_7 = load i8*, i8** %n

	%_8 = bitcast i8* %_7 to i8***
	%_9 = load i8**,i8*** %_8
	%_10 = getelementptr i8*,i8** %_9, i32 3
	%_11 = load i8*, i8** %_10
	%_12 = bitcast i8* %_11 to i8* (i8*)*
	%_13 = call i8* %_12(i8* %_7)
	%_14 = getelementptr i8, i8* %this, i32 16
	%_15 = bitcast i8* %_14 to i8**
	store i8* %_13, i8** %_15

	%_16 = getelementptr i8, i8* %this, i32 16
	%_17 = bitcast i8* %_16 to i8**
	%_18 = load i8*, i8** %_17

	%_19 = bitcast i8* %_18 to i8***
	%_20 = load i8**,i8*** %_19
	%_21 = getelementptr i8*,i8** %_20, i32 20
	%_22 = load i8*, i8** %_21
	%_23 = bitcast i8* %_22 to i32 (i8*,i8*)*
	%_24 = call i32 %_23(i8* %_18, i8* %this)
	store i32 %_24, i32* %nti

    br label %if2

if1:

	store i32 0, i32* %nti

    br label %if2

if2:

	%_25 = load i8*, i8** %n

	%_26 = bitcast i8* %_25 to i8***
	%_27 = load i8**,i8*** %_26
	%_28 = getelementptr i8*,i8** %_27, i32 8
	%_29 = load i8*, i8** %_28
	%_30 = bitcast i8* %_29 to i1 (i8*)*
	%_31 = call i1 %_30(i8* %_25)
    br  i1 %_31, label %if3, label %if4

if3:
	%_32 = load i8*, i8** %n

	%_33 = bitcast i8* %_32 to i8***
	%_34 = load i8**,i8*** %_33
	%_35 = getelementptr i8*,i8** %_34, i32 4
	%_36 = load i8*, i8** %_35
	%_37 = bitcast i8* %_36 to i8* (i8*)*
	%_38 = call i8* %_37(i8* %_32)
	%_39 = getelementptr i8, i8* %this, i32 8
	%_40 = bitcast i8* %_39 to i8**
	store i8* %_38, i8** %_40

	%_41 = getelementptr i8, i8* %this, i32 8
	%_42 = bitcast i8* %_41 to i8**
	%_43 = load i8*, i8** %_42

	%_44 = bitcast i8* %_43 to i8***
	%_45 = load i8**,i8*** %_44
	%_46 = getelementptr i8*,i8** %_45, i32 20
	%_47 = load i8*, i8** %_46
	%_48 = bitcast i8* %_47 to i32 (i8*,i8*)*
	%_49 = call i32 %_48(i8* %_43, i8* %this)
	store i32 %_49, i32* %nti

    br label %if5

if4:

	store i32 0, i32* %nti

    br label %if5

if5:

	ret i32 0
}
define i32 @MyVisitor.visit(i8* %this, i8* %.n)
{	%n = alloca i8*
	store i8* %.n, i8** %n
	%nti = alloca i32

	%_0 = load i8*, i8** %n

	%_1 = bitcast i8* %_0 to i8***
	%_2 = load i8**,i8*** %_1
	%_3 = getelementptr i8*,i8** %_2, i32 7
	%_4 = load i8*, i8** %_3
	%_5 = bitcast i8* %_4 to i1 (i8*)*
	%_6 = call i1 %_5(i8* %_0)
    br  i1 %_6, label %if0, label %if1

if0:
	%_7 = load i8*, i8** %n

	%_8 = bitcast i8* %_7 to i8***
	%_9 = load i8**,i8*** %_8
	%_10 = getelementptr i8*,i8** %_9, i32 3
	%_11 = load i8*, i8** %_10
	%_12 = bitcast i8* %_11 to i8* (i8*)*
	%_13 = call i8* %_12(i8* %_7)
	%_14 = getelementptr i8, i8* %this, i32 16
	%_15 = bitcast i8* %_14 to i8**
	store i8* %_13, i8** %_15

	%_16 = getelementptr i8, i8* %this, i32 16
	%_17 = bitcast i8* %_16 to i8**
	%_18 = load i8*, i8** %_17

	%_19 = bitcast i8* %_18 to i8***
	%_20 = load i8**,i8*** %_19
	%_21 = getelementptr i8*,i8** %_20, i32 20
	%_22 = load i8*, i8** %_21
	%_23 = bitcast i8* %_22 to i32 (i8*,i8*)*
	%_24 = call i32 %_23(i8* %_18, i8* %this)
	store i32 %_24, i32* %nti

    br label %if2

if1:

	store i32 0, i32* %nti

    br label %if2

if2:

	%_25 = load i8*, i8** %n

	%_26 = bitcast i8* %_25 to i8***
	%_27 = load i8**,i8*** %_26
	%_28 = getelementptr i8*,i8** %_27, i32 5
	%_29 = load i8*, i8** %_28
	%_30 = bitcast i8* %_29 to i32 (i8*)*
	%_31 = call i32 %_30(i8* %_25)
	call void (i32) @print_int(i32 %_31)

	%_32 = load i8*, i8** %n

	%_33 = bitcast i8* %_32 to i8***
	%_34 = load i8**,i8*** %_33
	%_35 = getelementptr i8*,i8** %_34, i32 8
	%_36 = load i8*, i8** %_35
	%_37 = bitcast i8* %_36 to i1 (i8*)*
	%_38 = call i1 %_37(i8* %_32)
    br  i1 %_38, label %if3, label %if4

if3:
	%_39 = load i8*, i8** %n

	%_40 = bitcast i8* %_39 to i8***
	%_41 = load i8**,i8*** %_40
	%_42 = getelementptr i8*,i8** %_41, i32 4
	%_43 = load i8*, i8** %_42
	%_44 = bitcast i8* %_43 to i8* (i8*)*
	%_45 = call i8* %_44(i8* %_39)
	%_46 = getelementptr i8, i8* %this, i32 8
	%_47 = bitcast i8* %_46 to i8**
	store i8* %_45, i8** %_47

	%_48 = getelementptr i8, i8* %this, i32 8
	%_49 = bitcast i8* %_48 to i8**
	%_50 = load i8*, i8** %_49

	%_51 = bitcast i8* %_50 to i8***
	%_52 = load i8**,i8*** %_51
	%_53 = getelementptr i8*,i8** %_52, i32 20
	%_54 = load i8*, i8** %_53
	%_55 = bitcast i8* %_54 to i32 (i8*,i8*)*
	%_56 = call i32 %_55(i8* %_50, i8* %this)
	store i32 %_56, i32* %nti

    br label %if5

if4:

	store i32 0, i32* %nti

    br label %if5

if5:

	ret i32 0
}

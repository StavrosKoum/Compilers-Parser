
@.Factorial_vtable = global [0 x i8*] []
@.Fac_vtable = global [2 x i8*] [i8* bitcast (i32 (i8*,i32)* @Fac.ComputeFac to i8*), i8* bitcast (i32 (i8*)* @Fac.testfun to i8*)]
@.B_vtable = global [3 x i8*] [i8* bitcast (i32 (i8*,i32)* @B.ComputeFac to i8*),i8* bitcast (i32 (i8*)* @Fac.testfun to i8*), i8* bitcast (i1 (i8*)* @B.foobar to i8*)]

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
	%_0 = call i8* @calloc(i32 1, i32 12)
	%_1 = bitcast i8* %_0 to i8***
	%_2 = getelementptr [2 x i8*], [2 x i8*]* @.Fac_vtable, i32 0, i32 0
	store i8** %_2, i8*** %_1
	%_3 = bitcast i8* %_0 to i8***
	%_4 = load i8**,i8*** %_3
	%_5 = getelementptr i8*,i8** %_4, i32 0
	%_6 = load i8*, i8** %_5
	%_7 = bitcast i8* %_6 to i32 (i8*,i32)*
	%_8 = call i32 %_7(i8* %_0, i32 10)
	call void (i32) @print_int(i32 %_8)

	ret i32 0
}
define i32 @Fac.ComputeFac(i8* %this, i32 %.num)
{	%num = alloca i32
	store i32 %.num, i32* %num
	%num_aux = alloca i32

	%_9 = getelementptr i8, i8* %this, i32 8
	%_10 = bitcast i8* %_9 to i32*
	store i32 1, i32* %_10

	%_11 = getelementptr i8, i8* %this, i32 8
	%_12 = bitcast i8* %_11 to i32*
	store i32 787, i32* %_12

	%_13 = load i32, i32* %num

	%_14 =icmp slt i32 %_13, 1
	br label %andexpre1

andexpre1:
	br i1 %_14, label %andexpre2, label %andexpre4

andexpre2:
	%_15 =icmp slt i32 1, 4
	br label %andexpre3

andexpre3:
	br label %andexpre4

andexpre4:
	%_16 = phi i1 [0, %andexpre1], [ %_15, %andexpre3 ]
    br  i1%_16, label %if0, label %if1

if0:
	store i32 1, i32* %num_aux

    br label %if2

if1:

	%_17 = load i32, i32* %num

	%_18 = bitcast i8* %this to i8***
	%_19 = load i8**,i8*** %_18
	%_20 = getelementptr i8*,i8** %_19, i32 0
	%_21 = load i8*, i8** %_20
	%_22 = bitcast i8* %_21 to i32 (i8*,i32)*
	%_23 = load i32, i32* %num

	%_24 = sub i32 %_23, 1
	%_25 = call i32 %_22(i8* %this, i32 %_24)
	%_26 = mul i32 %_17, %_25
	store i32 %_26, i32* %num_aux

    br label %if2

if2:

	%_27 = load i32, i32* %num_aux

	ret i32 %_27
}
define i32 @Fac.testfun(i8* %this)
{
	ret i32 1
}
define i1 @B.foobar(i8* %this)
{
	ret i1 1
}
define i32 @B.ComputeFac(i8* %this, i32 %.num)
{	%num = alloca i32
	store i32 %.num, i32* %num

	ret i32 1
}

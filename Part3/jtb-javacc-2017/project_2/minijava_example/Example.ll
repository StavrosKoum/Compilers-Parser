
@.Example_vtable = global [0 x i8*] [][]
@.A_vtable = global [2 x i8*][i8* bitcast (i32 (i8*,i32,i32)* @A.foo to i8*i8* bitcast (i32 (i8*,i32,i32)* @A.foo to i8*,i8* bitcast (i32 (i8*,i8*)* @A.bar to i8*,i8* bitcast (i32 (i8*,i8*)* @A.bar to i8*]
@.B_vtable = global [3 x i8*][i8* bitcast (i32 (i8*,i32,i32)* @A.foo to i8*
,i8* bitcast (i32 (i8*,i8*)* @A.bar to i8*
i8* bitcast (i1 (i8*,i1)* @B.foobar to i8*i8* bitcast (i1 (i8*,i1)* @B.foobar to i8*]

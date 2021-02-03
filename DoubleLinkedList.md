## DoubleLinkedList(双向链表)

<hr>

>#### 双向链表的遍历，添加，修改和删除




>+ #### 遍历
>
> 和单链表雷同，差异在于:双链表既可以从前向后遍历，也可以从后向前遍历
>
>+ #### 添加(默认添加到双链表的尾部)
>
> + 先找到双链表的尾部节点`temp`
> + `temp.next = newNode`
> + `newNode.pre = temp`
>
>+ #### 修改
>
> 修改针对节点中数据进行修改，和单链表相同（遍历找到对应节点，数据修改）
>
>+ #### 删除
>
> + 因为是双链表，因此我们可以实现节点的自我删除（不用再通过前一个节点修改next）
>
> + 直接找到待删除的节点`temp`
>
> + `temp.pre.next = temp.next`
>
> + `temp.next.pre = temp.pre`
>



<hr>

#### Output:

```java
===============DoubleLinkedList==============
===================添加完成==================
HeroNode1{no=1, name='宋江', nickname='及时雨'}
HeroNode1{no=2, name='卢俊义', nickname='玉麒麟'}
HeroNode1{no=3, name='吴用', nickname='智多星'}
HeroNode1{no=4, name='林冲', nickname='豹子头'}
===================删除完成==================
HeroNode1{no=1, name='宋江', nickname='及时雨'}
HeroNode1{no=2, name='卢俊义', nickname='玉麒麟'}
HeroNode1{no=4, name='林冲', nickname='豹子头'}
===================修改完成==================
HeroNode1{no=1, name='宋江', nickname='及时雨'}
HeroNode1{no=2, name='俊逸', nickname='麒麟'}
HeroNode1{no=4, name='林冲', nickname='豹子头'}
===================查询完成==================
HeroNode1{no=1, name='宋江', nickname='及时雨'}
===============DoubleLinkedList==============
```


/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.18 : Database - community
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`community` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_esperanto_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `community`;

/*Table structure for table `ad` */

DROP TABLE IF EXISTS `ad`;

CREATE TABLE `ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告id',
  `title` varchar(256) NOT NULL COMMENT '广告标题',
  `url` varchar(512) NOT NULL COMMENT '广告地址',
  `image` varchar(256) DEFAULT NULL COMMENT '广告图片',
  `gmt_start` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `gmt_end` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `gmt_create` bigint(20) DEFAULT NULL COMMENT '上架时间',
  `gmt_modified` bigint(20) DEFAULT NULL COMMENT '下架时间',
  `status` int(11) DEFAULT NULL,
  `pos` varchar(10) NOT NULL COMMENT '位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `ad` */

insert  into `ad`(`id`,`title`,`url`,`image`,`gmt_start`,`gmt_end`,`gmt_create`,`gmt_modified`,`status`,`pos`) values 
(1,'京东','https://search.jd.com/Search?keyword=java&enc=utf-8&pvid=8abae3e1099c497496b394853c7e4113',NULL,1605152930193,1636688930193,1605152930193,1636688930193,1,'NAV'),
(2,'图书馆','https://search.jd.com/Search?keyword=java&enc=utf-8&pvid=8abae3e1099c497496b394853c7e4113',NULL,1605152930,1636688930,1605152930,23,1,'NAV');

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `description` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '描述',
  `gmt_create` bigint(20) DEFAULT NULL COMMENT '文章发布的时间',
  `creator` int(30) DEFAULT NULL COMMENT '创建人的id',
  `tag` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`description`,`gmt_create`,`creator`,`tag`) values 
(1,'java 集成github登陆','# java 集成github登陆\r\n## 注册github账户，并创建github应用\r\n## github地址：[github](https://github.com/)\r\n### [创建github应用](https://blog.csdn.net/wei8023hzp/article/details/105249892)\r\n#### 编写代码\r\n1. **创建相应的实体类**\r\n```\r\n@Data\r\n@NoArgsConstructor\r\npublic class User {\r\n    /**\r\n     * id自增\r\n     */\r\n    private Integer id;\r\n    /**\r\n     * 唯一的标识符id\r\n     */\r\n    private String accountId;\r\n    /**\r\n     * `\r\n     * github用户名\r\n     */\r\n    private String name;\r\n    /**\r\n     * 请求头\r\n     */\r\n    private String token;\r\n    private Long gmtCreate;\r\n    private Long gmtModified;\r\n    /**\r\n     * 头像\r\n     */\r\n    private String avatarUrl;\r\n```\r\n2. **配置github应用，**\r\n```\r\n#github配置\r\n//唯一id\r\ngithub.client.id=Iv1.75c1e6b5781e0b66\r\n//令牌\r\ngithub,client.secret=ad8c3fb6696129196e22685ec340a5154fde4fcf\r\n//回调地址\r\ngithub.redirect.uri=http://localhost:8012/callback\r\n```\r\n3. **获取用户信息**\r\n```\r\n@Controller\r\n@Slf4j\r\npublic class AuthorizeController {\r\n    @Autowired\r\n    GithubProvider githubProvider;\r\n    @Value(\"${github.client.id}\")\r\n    private String Client_id;\r\n    @Value(\"${github,client.secret}\")\r\n    private String Client_secret;\r\n    @Value(\"${github.redirect.uri}\")\r\n    private String Redirect_urt;\r\n    @Autowired\r\n    UserMapper userMapper;\r\n    @Autowired\r\n    UserServiceImpl userService;\r\n\r\n    @GetMapping(\"/callback\")\r\n    public String Callback(@RequestParam(name = \"code\") String code,\r\n                           @RequestParam(name = \"state\") String state,\r\n                           HttpServletRequest servletRequest,\r\n                           HttpServletResponse servletResponse) {\r\n\r\n        //注入github账户\r\n        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();\r\n        accessTokenDTO.setClient_id(Client_id);\r\n        accessTokenDTO.setClient_secret(Client_secret);\r\n        accessTokenDTO.setCode(code);\r\n        accessTokenDTO.setRedirect_urt(Redirect_urt);\r\n        accessTokenDTO.setState(state);\r\n        String accessToken = githubProvider.getAccessToken(accessTokenDTO);\r\n        GithubUser githubUser = githubProvider.githubUser(accessToken);\r\n		System.out.println(githubUser.getName());\r\n```\r\n        if (githubUser != null) {\r\n            //获取github账户，并放入数据库\r\n            User user = new User();\r\n            String token = UUID.randomUUID().toString();\r\n            user.setToken(token);\r\n            user.setName(githubUser.getName());\r\n\r\n		user.setAccountId(String.valueOf(githubUser.getId()));\r\n            user.setAvatarUrl(githubUser.getAvatar_url());\r\n            //判断数据库是否有该用户的id\r\n            userService.createOrUpdate(user);\r\n            //自定义Cookie\r\n            servletResponse.addCookie(new Cookie(\"token\", token));\r\n            servletRequest.getSession().setAttribute(\"user\", githubUser);\r\n            return \"redirect:/\";\r\n        } else {\r\n            log.error(\"Callback get github error,{}\", githubUser);\r\n            return \"redirect:/\";\r\n        }\r\n    }\r\n\r\n4. ** 存入数据库**\r\n```\r\n    @Insert(\"insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) \" +\r\n            \"values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})\")\r\n    void insert(User user);\r\n```',1605790282595,1,'java,mysql,idea'),
(2,'java-手搓分页','# 分页\r\n- **定义相关状态**\r\n\r\n```\r\n@Data\r\npublic class PaginationDTO {\r\n    private List<ArticleDetailsDTO> detailsDTOS;\r\n    /**\r\n     * 是否展示向前箭头\r\n     */\r\n    private Boolean showPrevious;\r\n    /**\r\n     * 首页按钮\r\n     */\r\n    private Boolean showFirstPage;\r\n    /**\r\n     * 是否展示向后箭头\r\n     */\r\n    private Boolean showNext;\r\n    /**\r\n     * 尾页按钮\r\n     */\r\n    private Boolean showEndPage;\r\n    /**\r\n     * 当前页\r\n     */\r\n    private Integer page;\r\n    /**\r\n     * 当前配置数\r\n     */\r\n    private List<Integer> pages = new ArrayList<>();\r\n\r\n    private Integer totalPage;\r\n\r\n```\r\n\r\n- ** 代码实现**\r\n\r\n```\r\n public void setPaginagtion(Integer totalCount, Integer page, Integer size) {\r\n\r\n//        Integer totalPage;\r\n        if (totalCount % size == 0) {\r\n            totalPage = totalCount / size;\r\n        } else {\r\n            totalPage = totalCount / size + 1;\r\n        }\r\n\r\n        //修复容错\r\n        if (page < 1) {\r\n            page = 1;\r\n        }\r\n        if (page > totalPage) {\r\n            page = totalPage;\r\n        }\r\n        this.page = page;\r\n\r\n        pages.add(page);\r\n        for (int i = 1; i <= 3; i++) {\r\n            if (page - i > 0) {\r\n                pages.add(0, page - i);\r\n            }\r\n            if (page + i <= totalPage) {\r\n                pages.add(page + i);\r\n            }\r\n        }\r\n\r\n        // 是否展示上一页\r\n        showPrevious = (page == 1) ? false : true;\r\n        //是否展示下一页\r\n        showNext = (page == totalPage) ? false : true;\r\n        //是否展示第一页\r\n        showFirstPage = (pages.contains(1)) ? false : true;\r\n        //是否展示最后一页\r\n        showEndPage = (pages.contains(totalPage)) ? false : true;\r\n    }\r\n```',1605838961998,1,'java,springboot,idea'),
(3,'HashMap面试题知识大全','## HashMap常见面试题：\r\n\r\n1.HashMap的底层数据结构？\r\n2. HashMap的存取原理？\r\n3. Java7和Java8的区别？\r\n4. 为啥会线程不安全？\r\n5. 有什么线程安全的类代替么?\r\n6. 默认初始化大小是多少？为啥是这么多？为啥大小都是2的幂？\r\n7. HashMap的扩容方式？负载因子是多少？为什是这么多？\r\n8. HashMap的主要参数都有哪些？\r\n9. HashMap是怎么处理hash碰撞的？\r\n10. hash的计算规则？\r\n\r\n### HashMap深入浅出\r\n##### 1.你了解数据结构中的HashMap么？能跟我聊聊他的结构和底层原理么？\r\nHashMap是我们非常常用的数据结构，由数组和链表组合构成的数据结构。数组里面每个地方都存了Key-Value这样的实例，在Java7叫Entry在Java8中叫Node。因为他本身所有的位置都为null，在put插入的时候会根据key的hash去计算一个index值。\r\n\r\n##### 2.为啥需要链表，链表又是怎么样子的呢？\r\n我们都知道数组长度是有限的，在有限的长度里面我们使用哈希，哈希本身就存在概率性，两个不同的key，hash有一定的概率会一样，那就形成了链表。每一个节点都会保存自身的hash、key、value、以及下个节点。\r\n\r\n##### 3.新的Entry节点在插入链表的时候，是怎么插入的么？\r\njava8之前是头插法，就是说新来的值会取代原有的值，原有的值就顺推到链表中去，因为写这个代码的作者认为后来的值被查找的可能性更大一点，提升查找的效率。但是，在java8之后，都用尾部插入了\r\n\r\n##### 4.HashMap的扩容机制\r\n首先我们看下HashMap的扩容机制： 数组容量是有限的，数据多次插入的，到达一定的数量就会进行扩容，也就是resize。\r\n\r\n有两个因素： Capacity：HashMap当前长度。 LoadFactor：负载因子，默认值0.75f。\r\n\r\n怎么理解呢，就比如当前的容量大小为100，当你存进第76个的时候，判断发现需要进行resize了，那就进行扩容，但是HashMap的扩容也不是简单的扩大点容量这么简单的。\r\n\r\n分为两步 扩容：创建一个新的Entry空数组，长度是原数组的2倍。 ReHash：遍历原Entry数组，把所有的Entry重新Hash到新数组。\r\n\r\n长度扩大以后，Hash的规则也随之改变。 Hash的公式---> index = HashCode（Key） & （Length - 1） 原来长度（Length）是8你位运算出来的值是2 ，新的长度是16你位运算出来的值明显不一样了。\r\n\r\n##### 5.java8之后为啥改为尾部插入呢？\r\n我先举个例子吧，我们现在往一个容量大小为2的put两个值，负载因子是0.75是不是我们在put第二个的时候就会进行resize？ 2*0.75 = 1 所以插入第二个就要resize了\r\n\r\n现在我们要在容量为2的容器里面用不同线程插入A，B，C，假如我们在resize之前打个短点，那意味着数据都插入了但是还没resize那扩容前可能是这样的。\r\n\r\n我们可以看到链表的指向A->B->C\r\nTip：A的下一个指针是指向B的\r\n\r\n因为resize的赋值方式，也就是使用了单链表的头插入方式，同一位置上新元素总会被放在链表的头部位置，在旧数组中同一条Entry链上的元素，通过重新计算索引位置后，有可能被放到了新数组的不同位置上。\r\n\r\n就可能出现下面的情况，大家发现问题没有？\r\n\r\nB的下一个指针指向了A\r\n\r\n一旦几个线程都调整完成，就可能出现环形链表\r\n\r\n如果这个时候去取值，悲剧就出现了——Infinite Loop。\r\n\r\n使用头插会改变链表的上的顺序，但是如果使用尾插，在扩容时会保持链表元素原本的顺序，就不会出现链表成环的问题了。\r\n\r\n##### 6.Java8就可以把HashMap用在多线程中？\r\n通过源码看到put/get方法都没有加同步锁，多线程情况最容易出现的就是：无法保证上一秒put的值，下一秒get的时候还是原值，所以线程安全还是无法保证。\r\n\r\n7.HashMap的默认初始化长度是多少？那为啥用16不用别的呢？\r\n初始化大小是16\r\n\r\n因为在使用不是2的幂的数字的时候，Length-1的值是所有二进制位全为1，这种情况下，index的结果等同于HashCode后几位的值。 只要输入的HashCode本身分布均匀，Hash算法的结果就是均匀的。 这是为了实现均匀分布。\r\n\r\n##### 8.为啥我们重写equals方法的时候需要重写hashCode方法呢？你能用HashMap给我举个例子么？\r\n因为在java中，所有的对象都是继承于Object类。Ojbect类中有两个方法equals、hashCode，这两个方法都是用来比较两个对象是否相等的。\r\n\r\n在未重写equals方法我们是继承了object的equals方法，那里的 equals是比较两个对象的内存地址，显然我们new了2个对象内存地址肯定不一样\r\n\r\n对于值对象，==比较的是两个对象的值\r\n对于引用对象，比较的是两个对象的地址\r\n大家是否还记得我说的HashMap是通过key的hashCode去寻找index的，那index一样就形成链表了，也就是说”帅丙“和”丙帅“的index都可能是2，在一个链表上的。\r\n\r\n我们去get的时候，他就是根据key去hash然后计算出index，找到了2，那我怎么找到具体的”帅丙“还是”丙帅“呢？\r\n\r\nequals！是的，所以如果我们对equals方法进行了重写，建议一定要对hashCode方法重写，以保证相同的对象返回相同的hash值，不同的对象返回不同的hash值。\r\n\r\n不然一个链表的对象，你哪里知道你要找的是哪个，到时候发现hashCode都一样，这不是完犊子嘛。\r\n\r\n##### 8.你们是怎么处理HashMap在线程安全的场景么？\r\n我们一般都会使用HashTable或者ConcurrentHashMap，但是因为前者的并发度的原因基本上没啥使用场景了，所以存在线程不安全的场景我们都使用的是ConcurrentHashMap。 HashTable我看过他的源码，很简单粗暴，直接在方法上锁，并发度很低，最多同时允许一个线程访问，ConcurrentHashMap就好很多了，1.7和1.8有较大的不同，不过并发度都比前者好太多了。\r\n\r\n##### 8.HashMap怎么解决碰撞问题的?\r\nJava中HashMap是利用“拉链法”处理HashCode的碰撞问题。在调用HashMap的put方法或get方法时，都会首先调用hashcode方法，去查找相关的key，当有冲突时，再调用equals方法。hashMap基于hasing原理，我们通过put和get方法存取对象。当我们将键值对传递给put方法时，他调用键对象的hashCode()方法来计算hashCode，然后找到bucket（哈希桶）位置来存储对象。当获取对象时，通过键对象的equals()方法找到正确的键值对，然后返回值对象。HashMap使用链表来解决碰撞问题，当碰撞发生了，对象将会存储在链表的下一个节点中。hashMap在每个链表节点存储键值对对象。当两个不同的键却有相同的hashCode时，他们会存储在同一个bucket位置的链表中。键对象的equals()来找到键值对。',1605841888555,1,'java'),
(4,'数据结构之B-树、B+树','## B-树\r\nB-树其实就是B树，B树是一种多路平衡搜索树（非二叉），若其是M路，则：\r\n\r\n- 任意非叶子节点最多可以有M个子女，且M>2；\r\n- 根节点的子女数为[2,M]；\r\n- 除了根节点以外的非叶子节点的子女数目为M/2（取上整）个到M个；\r\n- 每个节点存放至少M/2-1（取上整）和至多M-1个键值（至少两个）；\r\n- 非叶子节点的关键字个数=指向子女的指针个数-1；\r\n- 非叶子节点的关键字K[1],K[2],…,K[M-1]且有K[i] < K[i+1]；\r\n- 非叶子节点的指针P[1],P[2],…,P[M]；其中P[1]指向关键字小于K[1]的子树，P[M]指向关键字大于K[M-1]的子树，其他P[i]指向关键字属于(K[i-1],K[i])的子树；\r\n- 所有叶子节点都位于同一层。\r\n- B树与二叉搜索树的最大区别在于其每个节点可以存不止一个键值，并且其子女不止两个，不过还是需要满足键值数=子女数-1。因此，对于相同数量的键值，B树比二叉搜索树要更加矮一些，特别是当M较大时，树高会更低。\r\n\r\n1. 一张图看懂一切 -\r\n\r\n[![](https://img-blog.csdn.net/20180805150951880?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3hkemhvdXhpbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)](http://https://img-blog.csdn.net/20180805150951880?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3hkemhvdXhpbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)\r\n\r\n### B-树的插入\r\n- B树的插入首先查找插入所在的节点，若该节点未满，插入即可，若该节点以及满了，则需要将该节点分裂，并将该节点的中间的元素移动到父节点上，若父节点未满，则结束，若父节点也满了，则需要继续分裂父节点，如此不断向上，直到根节点，如果根节点也满了，则分裂根节点，从而树的高度+1。\r\n\r\n### B-树的删除\r\n- B树的删除首先要找到删除的节点，并删除节点中的元素，如果删除的元素有左右孩子，则上移左孩子最右节点或右孩子最左节点到父节点，若没有左右孩子，则直接删除。删除后，若某节点中元素数目不符合B树要求（小于M/2-1取上整），则需要看起相邻的兄弟节点是否有多余的元素，若有，则可以向父节点借一个元素，然后将最丰满的相邻兄弟结点中上移最后或最前一个元素到父节点中（有点类似于左旋）。若其相邻兄弟节点没有多余的元素，则与其兄弟节点合并成一个节点，此时也需要将父节点中的一个元素一起合并。\r\n\r\n### B+树\r\n- B+树主要是应文件系统所需而产生的。文件系统中，文件的目录是一级一级索引，只有最底层的叶子节点（文件）保存数据。非叶子节点只保存索引，不保存实际的数据，数据都保存在叶子节点中，所有的非叶子节点都可以看成是索引部分。\r\n\r\n- B+树是B树的一个变种，其也是一种多路平衡搜索树，其与B树的主要区别是：\r\n\r\n- 非叶子节点的指针数量与关键字数量相等；\r\n- 非叶子节点的子树指针P[i]，指向关键字值属于[K[i],K[i+1]）的子树（B树是开区间，B+树是左闭右开，也就是说B树不允许关键字重复，而B+树允许）；\r\n- 所有关键字都在叶子节点出现，所有的叶子节点增加了一个链指针（稠密索引，且链表中的关键字切好是有序的）；\r\n- 非叶子节点相当于是叶子节点的索引（稀疏索引），叶子节点相当于是存储数据的数据层。\r\n一张图看懂一切 \r\n\r\n[![](https://img-blog.csdn.net/20180805150951880?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3hkemhvdXhpbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)](https://img-blog.csdn.net/20180805150951880?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3hkemhvdXhpbg==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)\r\n\r\n### B+树的插入\r\n- B+树的插入与B树类似，如果节点中有多余的空间放入元素，则直接插入即可。如果节点本来就已经满了，则将其分裂为两个节点，并将其中间元素的索引放入到父节点中，在这里如果是叶子节点的话，是拷贝中间元素的索引到父节点中（因为叶子节点需要包含所有的元素），而如果是非叶子节点，则是上移节点的中间元素到父节点中。\r\n\r\n### B+树的删除\r\n- 在叶节点中删除元素，如果节点还满足B+树的要求，则okay。如果元素个数过少，并且其邻近兄弟节点有多余的元素，则从邻近兄弟节点中借一个元素，并修改父节点中的索引使其满足新的划分。如果其邻近兄弟节点也没有多余的元素，则将其和邻近兄弟节点合并，并且我们需要修改其父节点的索引以满足新的划分。并且如果父节点的索引元素太少不满足要求，则需要继续看起兄弟节点是否多余，如果没有多余则还需要与兄弟节点合并，如此不断向上，直到根节点。如果根节点中元素也被删除，则把根节点删除，并由合并来的节点作为新的根节点，树的高度减1。\r\n\r\n### B-树和B+树的区别\r\n- B+树的非叶子节点并没有指向关键字具体信息的指针，因此其内部节点相对B树更小，如果把所有同一内部节点的关键字存放在同一盘块中，盘块所能容纳的关键字数量也越多，具有更好的空间局部性，一次性读入内存的需要查找的关键字也越多，相对的IO读写次数也就降低了。\r\n\r\n- 另外对于B+树来说，因为非叶子节点只是叶子节点中关键字的索引，所以任何关键字的查找都必须走一条从根节点到叶子节点的路，所有关键字查询的路径长度相同。而若经常访问的元素离根节点很近，则B树访问更迅速，因为其不一定要到叶子节点。\r\n\r\n- 数据库索引采用B+树的主要原因是B树在提高了IO性能的同时并没有解决元素遍历效率低下的问题，而也正是为了解决该问题，B+树应运而生。因为叶子节点中增加了一个链指针，B+树只需要取遍历叶子节点可以实现整棵树的遍历。而且数据库中基于范围的查询是非常频繁的，B树对基于范围的查询效率太低。',1605842258751,1,'java'),
(5,'数组和链表的区别浅析','### 1.链表是什么\r\n\r\n链表是一种上一个元素的引用指向下一个元素的存储结构，链表通过指针来连接元素与元素；\r\n\r\n链表是线性表的一种，所谓的线性表包含顺序线性表和链表，顺序线性表是用数组实现的，在内存中有顺序排列，通过改变数组大小实现。而链表不是用顺序实现的，用指针实现，在内存中不连续。意思就是说，链表就是将一系列不连续的内存联系起来，将那种碎片内存进行合理的利用，解决空间的问题。\r\n\r\n所以，链表允许插入和删除表上任意位置上的节点，但是不允许随即存取。链表有很多种不同的类型：单向链表、双向链表及循环链表。\r\n\r\n### 2.单向链表\r\n![](https://img-blog.csdn.net/20150303094256114?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSmFzbWluZV9zaGluZQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)\r\n\r\n单向链表包含两个域，一个是信息域，一个是指针域。也就是单向链表的节点被分成两部分，一部分是保存或显示关于节点的信息，第二部分存储下一个节点的地址，而最后一个节点则指向一个空值。\r\n\r\n### 3.双向链表\r\n![](https://img-blog.csdn.net/20150303101318960?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSmFzbWluZV9zaGluZQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)\r\n\r\n从上图可以很清晰的看出，每个节点有2个链接，一个是指向前一个节点（当此链接为第一个链接时，指向的是空值或空列表），另一个则指向后一个节点（当此链接为最后一个链接时，指向的是空值或空列表）。意思就是说双向链表有2个指针，一个是指向前一个节点的指针，另一个则指向后一个节点的指针。\r\n\r\n###4.循环链表\r\n\r\n![](https://img-blog.csdn.net/20150303103205580?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSmFzbWluZV9zaGluZQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)\r\n\r\n### 5.数组和链表的区别？\r\n\r\n不同：链表是链式的存储结构；数组是顺序的存储结构。\r\n\r\n链表通过指针来连接元素与元素，数组则是把所有元素按次序依次存储。\r\n\r\n链表的插入删除元素相对数组较为简单，不需要移动元素，且较为容易实现长度扩充，但是寻找某个元素较为困难；\r\n\r\n数组寻找某个元素较为简单，但插入与删除比较复杂，由于最大长度需要再编程一开始时指定，故当达到最大长度时，扩充长度不如链表方便。\r\n相同：两种结构均可实现数据的顺序存储，构造出来的模型呈线性结构。\r\n\r\n### 6.链表的应用、代码实践\r\n\r\n约瑟夫问题：\r\n\r\n      传说在公园1世纪的犹太战争中，犹太约瑟夫是公元一世纪著名的历史学家。在罗马人占领乔塔帕特后，39 个犹太人与约瑟夫及他的朋友躲到一个洞中，39个犹太人决定宁愿死也不要被敌人俘虏，于是决定了一个流传千古的自杀方式，41个人排成一个圆圈，由第1个人开始报数，每报到第3人该人就必须自杀，然后再由下一个人重新报数，直到所有人都自杀身亡为止。然而约瑟夫和他的朋友并不想遵从这个约定，约瑟夫要他的朋友先假装遵从，他将朋友与自己安排在第_个和第_个位置，于是逃过了这场死亡游戏,你知道安排在了第几个嘛？\r\n\r\n针对以上问题，使用单向循环链表的方式求解：\r\n\r\n                        //节点类\r\n			function Node(elemnt) {\r\n				this.item = elemnt;\r\n				this.next = null;\r\n			}\r\n			//循环列表需要修改一下构造函数，和遍历时候的判断条件\r\n			//构造函数如下；希望从后向前遍历，又不想要建立双向链表，就使用循环链表。\r\n			function Llist() {\r\n				this.head = new Node(\"1\");\r\n				this.head.next = this.head;\r\n				this.remove = remove;\r\n				this.insert = insert;\r\n				this.find = find;\r\n				this.display = display;\r\n				//..........\r\n			}\r\n			function find(number) {\r\n				var curr = this.head;\r\n				while (curr.item != number) {\r\n					curr = curr.next;\r\n				}\r\n				return curr;\r\n			}\r\n			function insert(element, newElement) {\r\n				var preNode = this.find(element);\r\n				var current = new Node(newElement);\r\n				current.next = preNode.next;\r\n				preNode.next = current;\r\n			}\r\n			function remove() {\r\n				var current = this.head;\r\n				console.log(\"remove\");		\r\n                       //跳过两个，杀死一个\r\n			while(current.next.next != null && current.item!=current.next.next.item){\r\n					var temp = current.next.next;\r\n					current.next.next = temp.next;\r\n					current = temp.next;\r\n					temp.next = null;\r\n				}\r\n				return current;\r\n			}\r\n			function display(flag,current) {\r\n				var crr = this.head;				\r\n				if(flag){\r\n					while(crr.next.item!=\"1\"){\r\n						console.log(crr.item);\r\n						crr=crr.next;\r\n					}\r\n				   }else{   //最后只剩两个直接输出\r\n					console.log(current.item);\r\n					console.log(current.next.item);\r\n				}\r\n			}\r\n			var Clist = new Llist();\r\n                        //输入排序\r\n			for (var i = 1; i < 41; i++){\r\n				Clist.insert(i.toString(),(i + 1).toString());\r\n			}\r\n                        //先输出所有\r\n			Clist.display(1,null);\r\n                        //通过remove返回最后杀死后的结果其中一个节点\r\n			Clist.display(0,Clist.remove());  //16,31\r\n\r\n组织代码的方式要学习体会；\r\n\r\n#### 7.自我理解\r\n\r\n1）数组便于查询和修改，但是不方便新增和删除\r\n\r\n2）链表适合新增和删除，但是不适合查询，根据业务情况使用合适的数据结构和算法是在大数据量和高并发时必须要考虑的问题',1605842718286,1,'java,数据结构'),
(6,'后端学习路线','### 该图无学习顺序，\r\n![](/images/md/444.jpg)\r\n',1605843274846,1,'java,mysql'),
(7,'基于Mysql数据库原理特性以及底层机制的深入研究理解','  Mysql作为关系型数据库的一种，它的开源免费特性以及支持百万级存储性能，备受互联网公司的喜爱，我个人在做研究生的项目以及去互联网公司实习的期间，大部分接触的也都是基于Mysql作为底层数据的存储，CRUD用的比较多，稍微复杂一点就是多条查询，各种内外连接以及group by操作，对于Mysql数据库原理特性以及底层机制的研究不够深入，因此，本篇文章主要是研究Mysql原理特性。（文章中截图来自于网上的好多博客）\r\n\r\n##（1）Mysql常用的引擎  \r\n### 1. InnoDB\r\nInnoDB 的存储文件有两个，后缀名分别是 .frm 和 .idb，其中 .frm 是表的定义文件，而 idb 是数据文件。\r\n\r\nInnoDB 中存在表锁和行锁，不过行锁是在命中索引的情况下才会起作用。\r\n\r\nInnoDB 支持事务，且支持四种隔离级别（读未提交、读已提交、可重复读、串行化），默认的为可重复读；而在 Oracle 数据库中，只支持串行化级别和读已提交这两种级别，其中默认的为读已提交级别。\r\n\r\n InnoDB 中的 B+Tree\r\n\r\n          InnoDB 是以 ID 为索引的数据存储。采用 InnoDB 引擎的数据存储文件有两个，一个定义文件，一个是数据文件。\r\n\r\nInnoDB 通过 B+Tree 结构对 ID 建索引，然后在叶子节点中存储记录。\r\n\r\n         若建索引的字段不是主键 ID，则对该字段建索引，然后在叶子节点中存储的是该记录的主键，然后通过主键索引找到对应的记录。\r\n\r\n\r\n![](https://img-blog.csdn.net/20180905091442503?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM1NTcxNTU0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)\r\n\r\n### 2. Myisam\r\n          Myisam 的存储文件有三个，后缀名分别是 .frm、.MYD、MYI，其中 .frm 是表的定义文件，.MYD 是数据文件，.MYI 是索引文件。Myisam 只支持表锁，且不支持事务。Myisam 由于有单独的索引文件，在读取数据方面的性能很高 。\r\n##### Myisam 中的 B+Tree：\r\n\r\n         Myisam 引擎也是采用的 B+Tree 结构来作为索引结构。由于 Myisam 中的索引和数据分别存放在不同的文件，所以在索引树中的叶子节点中存的数据是该索引对应的数据记录的地址，由于数据与索引不在一起，所以 Myisam 是非聚簇索引。\r\n\r\n![](https://img-blog.csdn.net/20180905091529876?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM1NTcxNTU0/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)\r\n\r\n  ###   InnoDB 和 Myisam 都是用 B+Tree 来存储数据的，目前常用的Mysql引擎是InnoDB，原因在于它支持行级锁、支持事务。\r\n \r\n\r\n（2）MySQL 的数据、索引存储结构\r\n      说到Mysql的数据、索引存储结构，就得先介绍Mysql数据库数据的存储方式以及Mysql的索引了。\r\n\r\n### 1、Mysql数据库数据存储的原理\r\n        数据库数据都是以磁盘文件的方式存储到系统当中的，存储方式如下：信息存储在硬盘里，硬盘是由很多的盘片组成，通过盘片表面的磁性物质来存储数据。\r\n\r\n ###   A、访盘请求完成过程\r\n     硬盘在逻辑上被划分为磁道、柱面以及扇区。\r\n\r\n          1）确定磁盘地址（柱面号，磁头号，扇区号），内存地址（源 / 目）：当需要从磁盘读取数据的时候，系统会将数据的逻辑地址传递个磁盘，磁盘的控制电路按照寻址逻辑将逻辑地址翻译成物理地址，即确定要读的数据在哪个磁道，哪个扇区。\r\n\r\n             2）为了读取这个扇区的数据，需要将磁头放到这个扇区上方，为了实现这一点：\r\n\r\n              A. 首先必须找到柱面，即磁头需要移动对准相应磁道，这个过程叫做寻道，所耗费时间叫做寻道时间。\r\n\r\n              B. 然后目标扇区旋转到磁头下，即磁盘旋转将目标扇区旋转到磁头下，这个过程耗费的时间叫做旋转时间。\r\n\r\n              3）即一次访盘请求（读 / 写）完成过程由三个动作组成：\r\n\r\n               A. 寻道（时间）：磁头移动定位到指定磁道。\r\n\r\n               B. 旋转延迟（时间）：等待指定扇区从磁头下旋转经过。\r\n\r\n               C. 数据传输（时间）：数据在磁盘与内存之间的实际传输。\r\n\r\n## B、磁盘的读写原理\r\n       系统将文件存储到磁盘上时，按柱面、磁头、扇区的方式进行，即最先是第 1 磁道的第一磁头下的所有扇区，然后是同一柱面的下一个磁头……\r\n\r\n         一个柱面存储满后就推进到下一个柱面，直到把文件内容全部写入磁盘。系统也以相同的顺序读出数据，读出数据时通过告诉磁盘控制器要读出扇区所在柱面号、磁头号和扇区号（物理地址的三个组成部分）进行。\r\n\r\n##   C、减少 I/O 的预读原理\r\n\r\n## 磁盘读取文件，效率太低，因此需要减少访问磁盘IO的频率。\r\n\r\n  ####  磁盘预读原理：\r\n  \r\n  磁盘往往不是严格地按需读取，而是每次都会预读，即使只需要一个字节，磁盘也会从这个位置开始，顺序向后读取一定长度的数据放入内存。\r\n\r\n这样做的理论依据是计算机科学中著名的局部性原理：\r\n当一个数据被用到时，其附近的数据一般来说也会被马上使用。\r\n\r\n程序运行期间所需要的数据通常比较集中。\r\n\r\n由于磁盘顺序读取的效率很高（不需要寻道时间，只需要很少的旋转时间），因此对于具有局部性的程序来说，预读可以提高 I/O 效率。\r\n  预读的长度一般为页（Page）的整数倍。页是计算机管理存储器的逻辑块，硬件及操作系统往往将主存和磁盘存储分割为连续的大小相等的块。\r\n  \r\n  每个存储块称为一页（在许多操作系统中，页的大小通常为 4k），主存和磁盘以页为单位交换数据，当程序要读取的数据不在主存中时，会触发一个缺页异常。    此时系统会向磁盘发出读盘信息，磁盘会找到数据的起始位置并向后连续读取一页或几页的数据载入内存中，然后异常返回，程序继续运行。\r\n\r\n\r\n## 2、Mysql索引\r\n        索引是一种用来实现 MySQL 高效获取数据的数据结构。我们通常所说的在某个字段上建索引，意思就是让 MySQL 对该字段以索引这种数据结构来存储，然后查找的时候就有对应的查找算法。\r\n\r\n ###      建立索引的原因：\r\n                 为了查找的优化，特别是当数据很庞大的时候，采用特殊的查找算法，可以实现数据的高效快速查询。\r\n\r\n###       特殊查找算法：\r\n                 Mysql数据库索引采用的数据结构是B tree和B+ tree。\r\n\r\n        为什么要采用B tree和B+ tree？\r\n                一般的查找算法有顺序查找、折半查找、快速查找等，但是每种查找算法都只能应用于特定的数据结构之上，例如顺序查找依赖于顺序结构，折半查找通过二叉查找树或红黑树实现二分搜索。这样的索引数据结构还是会对数据库的数据结构有要求，而且对磁盘IO的操作依旧很频繁。因此采用了B树和B+ 树.\r\n\r\n               注：二分查找算法的时间复杂度计算：\r\n\r\n                    比如：总共有n个元素，每次查找的区间大小就是n，n/2，n/4，…，n/2^k（接下来操作元素的剩余个数），其中k就是循环的次数。 由于n/2^k取整后>=1，即令n/2^k=1， 可得k=log2n,（是以2为底，n的对数），所以时间复杂度可以表示O()=O(logn)\r\n\r\n##                    红黑树：\r\n\r\n                             一种特殊的二叉查找树，红黑树的应用比较广泛，主要是用它来存储有序的数据，它的时间复杂度是O(lgn)，效率非常之高。\r\n\r\n        B树与B+树的底层:\r\n              B树也称B-树,它是一颗平衡多路查找树。一般来说，索引本身也很大，不可能全部存储在内存中，因此索引往往以索引文件的形式存储的磁盘上。这样的话，索引查找过程中就要产生磁盘I/O消耗。B-tree算法减少定位记录时所经历的中间过程，从而加快存取速度',1605843858122,1,'mysql,数据结构');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `parent_id` int(30) DEFAULT NULL COMMENT '文章的id',
  `type` int(30) DEFAULT NULL COMMENT '发布人的id',
  `commentator` int(30) NOT NULL COMMENT '评论人的id',
  `gmt_create` bigint(30) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '更新时间',
  `like_count` int(30) DEFAULT '0' COMMENT '点赞数',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回复类容',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

/*Table structure for table `details` */

DROP TABLE IF EXISTS `details`;

CREATE TABLE `details` (
  `id` int(30) NOT NULL COMMENT '与creator相关联',
  `comment_count` int(10) DEFAULT '0' COMMENT '关注数量',
  `view_count` int(10) DEFAULT '0' COMMENT '浏览',
  `like_count` int(10) DEFAULT '0' COMMENT '点赞',
  `reply_count` int(10) DEFAULT '0' COMMENT '回复数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `details` */

insert  into `details`(`id`,`comment_count`,`view_count`,`like_count`,`reply_count`) values 
(1,0,14,0,0),
(2,0,0,0,0),
(3,0,1,0,0),
(4,0,1,0,0),
(5,0,2,0,0),
(6,0,4,0,0),
(7,0,2,0,0);

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `notifier` int(30) NOT NULL COMMENT '评论文章的人',
  `receiver` int(30) NOT NULL COMMENT '接收评论的人',
  `outer_id` int(30) NOT NULL COMMENT '当前文章的id',
  `type` int(30) NOT NULL COMMENT '区分回复类型',
  `gmt_create` bigint(100) NOT NULL COMMENT '回复时间',
  `status` int(30) NOT NULL DEFAULT '0' COMMENT '判断是否查看过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notification` */

/*Table structure for table `relatedvideo` */

DROP TABLE IF EXISTS `relatedvideo`;

CREATE TABLE `relatedvideo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '视频id',
  `url` varchar(215) DEFAULT NULL COMMENT '视频封面',
  `page` varchar(30) NOT NULL COMMENT '视频标识page',
  `aid` varchar(50) NOT NULL COMMENT '视频标识aid',
  `name` varchar(50) DEFAULT NULL COMMENT '视频发布人',
  `address` varchar(512) DEFAULT NULL COMMENT '作者地址',
  `heading` varchar(512) DEFAULT NULL COMMENT '视频标题',
  `gmt_create` bigint(20) NOT NULL COMMENT '视频发布的时间',
  `identification` int(2) NOT NULL COMMENT '视频是否有多条数据',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `relatedvideo` */

insert  into `relatedvideo`(`id`,`url`,`page`,`aid`,`name`,`address`,`heading`,`gmt_create`,`identification`) values 
(1,'//i2.hdslb.com/bfs/archive/aa8ebeb1511d398cfb44b1c5486b2577ecb154c3.jpg','1','73905243','一个爱吃的猪','https://space.bilibili.com/60509716?from=search&seid=8828880611691153071','纳兰容若】那些让人一眼泪目的话，那些经典或致郁的句子文摘。',1572426031000,0),
(2,' //i1.hdslb.com/bfs/archive/c9958e7a5a8553c1a93aa1787ef5847e06748650.jpg@380w_240h_100Q_1c.webp','1','75233634','遇见狂神说','https://space.bilibili.com/95256449?spm_id_from=333.788.b_765f7570696e666f.1','【狂神说Java】SpringBoot最新教程IDEA版通俗易懂',1573383260000,1),
(3,'//i0.hdslb.com/bfs/archive/60fcbdcdba745d813632f1aef309baf2e8497aa7.jpg@400w_250h.webp','1','56600409','一个爱吃的猪','https://space.bilibili.com/60509716?spm_id_from=333.788.b_765f7570696e666f.1','【人间失格】那些让人一眼泪目的话，那些经典或致郁的句子文摘。',1561293965000,0),
(4,'//i2.hdslb.com/bfs/archive/6a7ff4563ce50b38bb2dd0f59530642ed2af04c4.jpg@400w_250h.webp','1','56353519','一个爱吃的猪','https://space.bilibili.com/60509716?spm_id_from=333.788.b_765f7570696e666f.1','【雪中悍刀行】那些让人一眼泪目的话，那些经典或致郁的句子文摘。',1561128680000,0),
(5,'//i0.hdslb.com/bfs/archive/b9757aa2ad96286ed3ba445f475faa9ac27cd3b7.jpg@160w_100h_100Q_1c.webp','1','202459765','一个爱吃的猪','https://space.bilibili.com/60509716?spm_id_from=333.788.b_765f7570696e666f.1','【句集】年轻时不要遇见太惊艳的人 | 我们那时的爱情',1602584409000,0),
(6,'//i1.hdslb.com/bfs/archive/2c9e6a4b9dec9a99d2ab410f3e8834fd47d6dee3.jpg@400w_250h.webp','1','57248830','一个爱吃的猪','https://space.bilibili.com/60509716?spm_id_from=333.788.b_765f7570696e666f.1','【剑来】那些让人一眼泪目的话，那些经典或致郁的句子文摘。',1601108149000,0),
(7,'//i0.hdslb.com/bfs/archive/ad32153880d06efc4ce5f891515d7e782a76930f.jpg@160w_100h_100Q_1c.webp','1','329631755','一观者也','https://space.bilibili.com/10285240?spm_id_from=333.788.b_765f7570696e666f.1','那些虐到骨子里的诗词句子｜一句诗词就是一篇虐文',1601108149000,0),
(8,'//i0.hdslb.com/bfs/archive/f882fec16b9d1abf286e7b21b9bb4ab6564c9fc2.jpg@400w_250h.webp','1','89223081','我们的小喜','https://search.bilibili.com/all?keyword=%E6%88%91%E4%BB%AC%20%E7%9A%84%E5%B0%8F%E5%96%9C','［自制烧烤神器］烧 烤 顿顿 都 不 能 少，拯救烧烤强迫症的福音来了！',1581775734000,0),
(9,'//i1.hdslb.com/bfs/archive/avsas_i181025wsmss88fdnvxgtl4y83a9eemf_0021.jpg@400w_250h.webp','1','34552880','我们的小喜','https://search.bilibili.com/all?keyword=%E6%88%91%E4%BB%AC%20%E7%9A%84%E5%B0%8F%E5%96%9C','雨天在自家小院烧肉，别有一番滋味',1540437849000,0),
(10,'//i1.hdslb.com/bfs/archive/c8581cf400216316c0d81cb443fc226514c4f346.jpg@400w_250h.webp','1','92990389','我们的小喜','https://search.bilibili.com/all?keyword=%E6%88%91%E4%BB%AC%20%E7%9A%84%E5%B0%8F%E5%96%9C','［野菜宴］上山挖野菜的时候，我遇到了早春的美好！',1583097180000,0),
(11,'//i2.hdslb.com/bfs/archive/664de8d711485fee512d0990bfb4a36f3f37899c.jpg@400w_250h.webp','1','455909023','我们的小喜','https://search.bilibili.com/all?keyword=%E6%88%91%E4%BB%AC%20%E7%9A%84%E5%B0%8F%E5%96%9C&page=2','【梁弄大糕】复刻这道传承百年的江南风味糕点，出锅那一刻，是惊喜还是翻车呢？',1591422097000,0),
(12,'//i0.hdslb.com/bfs/archive/8b5e91d7d2b33a029865cfbfa1f81c1288ac3883.jpg@160w_100h_100Q_1c.webp','1','838872261','一观者也','https://space.bilibili.com/10285240?spm_id_from=333.788.b_765f7570696e666f.1','诸 子 百 家 思 想 之 美',1594281234000,0);

/*Table structure for table `secomment` */

DROP TABLE IF EXISTS `secomment`;

CREATE TABLE `secomment` (
  `id` int(30) NOT NULL AUTO_INCREMENT COMMENT '二级评论id',
  `capacity` text NOT NULL COMMENT '评论类容',
  `gmt_create` bigint(30) NOT NULL COMMENT '评论时间',
  `comment_id` int(30) NOT NULL COMMENT '被评论的id',
  `people_id` int(30) NOT NULL COMMENT '评论人的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `secomment` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `token` char(36) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `avatar_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`account_id`,`name`,`token`,`gmt_create`,`gmt_modified`,`avatar_url`) values 
(1,'69623267','pawn','484d3c5a-58be-4d57-bc82-e76de1d6809e',1603784661949,1605919556275,'https://avatars3.githubusercontent.com/u/69623267?v=4');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

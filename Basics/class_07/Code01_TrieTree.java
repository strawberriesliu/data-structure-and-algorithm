package Basics.class_07;

public class Code01_TrieTree {

	public static class TrieNode{
		public int path;
		public int end;
		public TrieNode[] nexts;

		public TrieNode(){
			path = 0;
			end = 0;
			nexts = new TrieNode[26];
		}
	}

	public static class Trie {
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			if(word == null) return;
			TrieNode node = root;
			char[] chars = word.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				int index = chars[i] - 'a';
				if(node.nexts[index] == null){
					node.nexts[index] = new TrieNode();
				}
				node = node.nexts[index];
				node.path++;
			}
			node.end++;
		}

		// word这个单词加入过几次
		public int search(String word) {
			if(word == null) return 0;
			TrieNode node = root;
			char[] chars = word.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				int index = chars[i] - 'a';
				if(node.nexts[index] == null){
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}

		public void delete(String word) {
			if(search(word) == 0) return;
			TrieNode node = root;
			char[] chars = word.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				int index = chars[i] - 'a';
				if(--node.nexts[index].path == 0){
					node.nexts[index] = null;
					return;
				}
				node = node.nexts[index];
			}
			node.end--;

		}

		// 所有加入的字符串中，有多少是以pre这个字符串作为前缀的
		public int prefixNumber(String pre) {
			if(pre == null) return 0;
			TrieNode node = root;
			char[] chars = pre.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				int index = chars[i] - 'a';
				if(node.nexts[index] == null){
					return 0;
				}
				node = node.nexts[index];
			}
			return node.path;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.search("zuoa"));
		System.out.println(trie.prefixNumber("zuo"));

	}

}

package app.SARA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class TagGenerator {
    private ArrayList<String> memoryTag = new ArrayList<>(Arrays.asList("mem"));
    private ArrayList<String> uiTag = new ArrayList<>(Arrays.asList("ui", "gui", "view", "window", "fx", "swing", "awt", "android"));
    private ArrayList<String> networkTag = new ArrayList<>(Arrays.asList("net", "http", "socket", "url", "uri", "ftp", "smtp", "imap", "pop3", "tcp", "udp", "ssl", "tls", "ssh", "dns", "dhcp", "ip", "icmp", "arp", "rarp", "icmpv6", "igmp", "bgp", "eigrp", "ospf", "rip", "pim", "lldp", "stp", "vtp", "lacp", "lldp", "cdp", "csrf"));
    private ArrayList<String> storageTag = new ArrayList<>(Arrays.asList("db", "sql", "nosql", "h2", "mysql", "mariadb", "postgresql", "oracle", "mongodb", "cassandra", "redis", "memcached", "couchdb", "couchbase", "ne"));
    private ArrayList<String> ioTag = new ArrayList<>(Arrays.asList("io", "file", "stream", "reader", "writer", "input", "output", "inputstream", "outputstream", "bufferedreader", "bufferedwriter", "fileinputstream", "fileoutputstream", "filewriter", "filereader", "filechannel", "filelock", "filedescriptor", "System.in", "System.out", "System.err", "console", "scanner", "printwriter", "printstream", "datainputstream", "dataoutputstream", "objectinputstream", "objectoutputstream", "objectinput", "objectoutput", "objectstream", "objectwriter", "objectreader", "objectchannel", "objectlock", "objectdescriptor", "objectinputstream", "objectoutputstream", "objectwriter", "objectreader", "objectchannel", "objectlock", "objectdescriptor", "objectinputstream", "objectoutputstream", "objectwriter", "objectreader", "objectchannel", "objectlock", "objectdescriptor", "objectinputstream", "objectoutputstream", "objectwriter", "objectreader", "objectchannel", "objectlock", "objectdescriptor"));

//    Backup
    public HashSet<String> getTags(ArrayList<String> codeSnippet) {
        HashSet<String> tags = new HashSet<>();
        for (String token : codeSnippet) {
            if (isTag(token, memoryTag)) {
                tags.add("memory");
            } else if (isTag(token, uiTag)) {
                tags.add("ui");
            } else if (isTag(token, networkTag)) {
                tags.add("network");
            } else if (isTag(token, storageTag)) {
                tags.add("storage");
            } else if (isTag(token, ioTag)) {
                tags.add("io");
            }
        }
        return tags;
    }
    private boolean isTag(String token, ArrayList<String> tags) {
        boolean flag = false;
        for (String tag : tags) {
            if (token.toLowerCase().contains(tag)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}

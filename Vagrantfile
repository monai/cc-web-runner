# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.define "centos72" do |config|
    config.vm.box = "boxcutter/centos72"
    config.vm.provision "shell", inline: $script
    config.vm.network "private_network", ip: "192.168.99.4"
  end
end

$script = <<SCRIPT
yum -y group install "Development Tools"
yum -y install https://rpm.nodesource.com/pub_4.x/el/7/x86_64/nodesource-release-el7-1.noarch.rpm
yum install -y mc nodejs rpm-build vim wget
wget -q --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u102-b14/jdk-8u102-linux-x64.rpm
yum -y install jdk-8u102-linux-x64.rpm
rm -f jdk-8u102-linux-x64.rpm
systemctl start vboxadd
SCRIPT

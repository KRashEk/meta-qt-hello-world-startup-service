SUMMARY = "Qt Hello world Recipe"
DESCRIPTION = "This recipe builds a Qt project for a Hello world application."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/KRashEk/qt-hello-world;branch=master"
SRCREV  = "333f3d2c980e69fb881f9056c4c6612a83d1e2d8"

DEPENDS += "qtbase"

S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}/usr/bin/
    install -m 0755 qt-hello-world ${D}/usr/bin/

    install -d ${D}/etc/systemd/system
    install -m 0644 ${WORKDIR}/qtautorun.service ${D}/etc/systemd/system
    install -m 0755 ${WORKDIR}/qt-start.sh ${D}/usr/bin/
}

FILES_${PN} +=  "\${systemd_unitdir}/system/qtautorun.service"


SRC_URI_append = " file://*"


inherit qmake5 systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "qtautorun.service"

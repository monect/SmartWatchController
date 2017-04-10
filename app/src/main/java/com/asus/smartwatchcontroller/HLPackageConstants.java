package com.asus.smartwatchcontroller;

/**
 * Created by Monect on 25/11/2016.
 */

public class HLPackageConstants
{
    private HLPackageConstants() {
        throw new RuntimeException("Can't instantiate this class!!");
    }

    public enum DIR
    {
        APP_TO_DEV,
        DEV_TO_APP;
    }

    public enum ROLE
    {
        RESPONSOR,
        SPONSOR;
    }

    public enum TYPE_MESSAGE
    {
        CALENDAR {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new CalendarNotify();
            }

            @Override
            int getMinimumBodyLength() {
                return 1;
            }
        },
        COMMON_DATA {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new CommonData();
            }
        },
        DEV_BIND {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new DevBind();
            }
        },
        DEV_SETTING {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new DevSetting();
            }
        },
        DEV_STATUS {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new DevStatus();
            }

            @Override
            int getMinimumBodyLength() {
                return 2;
            }
        },
        DOZE_DATA {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new DozeData();
            }
        },
        EXERCISE_DATA {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new ExerciseHeader();
            }
        },
        GPS {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new GpsData();
            }
        },
        MAIL {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new Mail();
            }

            @Override
            int getMinimumBodyLength() {
                return 1;
            }
        },
        MUSIC {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new Music();
            }
        },
        NOTIFICATION {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new Notification();
            }

            @Override
            int getMinimumBodyLength() {
                return 1;
            }
        },
        PHONE {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new Phone();
            }

            @Override
            int getMinimumBodyLength() {
                return 1;
            }
        },
        SLEEP_DATA {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new SleepData();
            }
        },
        SMS {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new SMS();
            }

            @Override
            int getMinimumBodyLength() {
                return 1;
            }
        },
        SYNC_TIME {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new SyncTime();
            }
        },
        WEATHER {
            @Override
            HLBluetoothMessage getClassInstance() {
                return new Weather();
            }
        };

        abstract HLBluetoothMessage getClassInstance();

        int getMinimumBodyLength() {
            return 4;
        }
    }

    public enum TYPE_SEQUENCE
    {
        BEGIN,
        END,
        MIDDLE,
        ONE;
    }
}

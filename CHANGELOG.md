# v0.1.1 (3/20/2025)
Sorry it's been so long, I've been fairly busy with university and have been unable to work on the mod. It'll be a while before any new features are added, but here's some quick fixes for now. I also plan on doing some version ports as well.

## Fixed
- Both Hanging and Wall Hanging Banners didn't drop anything when broken
  - Wow, that was a big oversight, I really should've done more survival testing
- Hanging Banners couldn't be mined faster with an axe
- Hanging Banners could be placed on the underside of non-solid blocks



# v0.1.0 (1/12/2025)

## Added

### New Banner Patterns
- Grady Dexter
- Grady Sinister
- Grady per Fess
- Grady per Pale
- Grady in Annulo

- Barry
- Bendy Dexter
- Bendy Sinister

- Chevronny Chief
- Chevronny Base

- Checky
- Checky Inverted

- Fess Chief
- Fess Base
- Pale Dexter
- Pale Sinister

- Orle

- Chevron Chief
- Chevron Base

- Pall Chief
- Pall Base

- Pile Chief
- Pile Base

- Flaunch Dexter
- Flaunch Sinister

- Annulet
- Lozenge
- Mascle
- Escutcheon

- Label Chief
- Label Base

- Mullet Charge
  - Requires pattern item, made with Glowstone Dust

### Hanging Banners
Banners can now be placed on either the bottom of a block or the side of a block, similar to hanging signs. They can also connect with hanging signs, allowing for creative decorations.

### Loom Tooltips
When hovering over a banner pattern in the loom, there is now a tooltip, containing the name and ID of the pattern. This can be disabled in the config in order to restore the vanilla behavior.


## Changed

### Retextured Banners and Patterns
The banner has been retextured to be a little less noisy and clean. Along with this, many of the vanilla banner patterns have also been retextured, smoothing them out. There should hopefully be little difference to your existing designs.

### Renamed Banner Patterns
Some of the vanilla banner patterns didn't fit into the naming scheme of heraldry (read above), so they have been updated to fit in (well, some were changed to be less accurate, but more explicit). None of the designs have been significantly changed, only the names.

- Per Fess → Per Fess Chief
- Per Fess Inverted → Per Fess Base
- Per Pale → Per Pale Dexter
- Per Pale Inverted → Per Pale Sinister

- Per Bend Sinister → Per Bend Sinister Chief
- Per Bend → Per Bend Dexter Chief
- Per Bend Inverted → Per Bend Dexter Base
- Per Bend Sinister Inverted → Per Bend Sinister Base

- Chevron → Per Saltire Base
- Inverted Chevron → Per Saltire Chief

- Gradient → Grady Chief
- Base Gradient → Grady Base

- Field Masoned → Masoned

- Pale Dexter → Tierce Dexter
- Pale Sinister → Tierce Sinister
- Bend → Bend Dexter

- Chief Dexter Canton → Canton Chief Dexter
- Chief Sinister Canton → Canton Chief Sinister
- Base Dexter Canton → Canton Base Dexter
- Base Sinister Canton → Canton Base Sinister

- Lozenge → Fusil

- Thing → Thing Charge
- Globe → Globe Charge
- Snout → Snout Charge
- Flow → Flow Charge
- Guster → Guster Charge

### More Banner Layers
Having a limit of only 6 banner layers is both arbitrary and incredibly limiting. Now, there's a default limit of 32 layers, which is configurable.

To avoid having a mile-long tooltip, the tooltip will only display, by default, the most recent 8 layers, with the rest being trimmed. The limit for this is also configurable.

### Wearable Banners
Even in vanilla, banners have functionality for rendering on the player's head. Now, banners are able to simply be placed into your helmet slot.

### Stackable Banners
As a simple quality of life change, banners now have a default stack size of 64. Once again, this is configurable.

### Updated Banner Tooltip
Banners, rather than the tooltip being an unlabeled list of banner patterns, now have a tooltip more in-line with the modern style of tooltip. The list now is titled "Patterns", and indented, similar to the tooltip of Smithing Templates.

As a bonus, this fixes [MC-217525](https://bugs.mojang.com/browse/MC-217525).

#### Notice
Banner Pattern tooltips have also been changed to have more simplified translations. Since this changes the translation key, this will break a lot of other custom banner patterns' translations. **Reach out on the Discord to request custom translations be added for a datapack/mod!**